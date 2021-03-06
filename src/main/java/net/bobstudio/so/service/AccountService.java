package net.bobstudio.so.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import net.bobstudio.so.domain.Account;
import net.bobstudio.so.domain.Role;
import net.bobstudio.so.dto.PageVo;
import net.bobstudio.so.repository.AccountDao;
import net.bobstudio.so.security.ShiroDbRealm.ShiroUser;
import net.bobstudio.so.service.exception.ErrorCode;
import net.bobstudio.so.service.exception.ServiceException;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.utils.Ids;
//import org.springside.modules.persistence.Hibernates;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import static net.bobstudio.so.security.CustomCredentialsMatcher.hashPassword;

// Spring Bean的标识.
@Service
public class AccountService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private AccountDao accountDao;

	// 注入配置值
	@Value("${app.loginTimeoutSecs:600}")
	private int loginTimeoutSecs;

	// codehale metrics
	@Autowired
	private CounterService counterService;

	// guava cache
	private Cache<String, Account> loginUsers;

	@PostConstruct
	public void init() {
		loginUsers = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(loginTimeoutSecs, TimeUnit.SECONDS)
				.build();
	}

	@Transactional(readOnly = true)
	public String loginWithStock(String code, String password, String right) {
		Account account = accountDao.findByCode(code);

		if (account == null) {
			throw new ServiceException("User not exist", ErrorCode.FORBIDDEN);
		}

		if (!account.password.equals(hashPassword(password)) || !hasRight(account, right)) {
			throw new ServiceException("Wrong password or right", ErrorCode.UNAUTHORIZED);
		}

		String token = Ids.uuid2();
		loginUsers.put(token, account);
		counterService.increment("loginUser");
		return token;
	}

	private boolean hasRight(Account account, String right) {
		if(!"stock:edit".equals(right)) {
			return false;
		}
		
		for (Role role : account.roleList) {
			if (role.priv.indexOf(right) != -1) {
				return true;
			}
		}

		return false;
	}

	public void logout(String token) {
		Account account = loginUsers.getIfPresent(token);
		if (account == null) {
			logger.warn("logout an alreay logout token:" + token);
		} else {
			loginUsers.invalidate(token);
			counterService.decrement("loginUser");
		}
	}

	public Account getLoginUser(String token) {

		Account account = loginUsers.getIfPresent(token);

		if (account == null) {
			throw new ServiceException("User doesn't login", ErrorCode.UNAUTHORIZED);
		}

		return account;
	}

	@Transactional
	public void register(String email, String name, String password) {

		if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
			throw new ServiceException("Invalid parameter", ErrorCode.BAD_REQUEST);
		}

		Account account = new Account();
		account.email = email;
		account.name = name;
		account.password = hashPassword(password);
		accountDao.save(account);
	}

	@Transactional(readOnly = true)
	public Iterable<Account> findAll() {
		return accountDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<Account> findAll(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = PageVo.buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Account> spec = buildSpecification(searchParams);

		return accountDao.findAll(spec, pageRequest); 
	}

	private Specification<Account> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		//filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<Account> spec = DynamicSpecifications.bySearchFilter(filters.values(), Account.class);
		return spec;
	}

	@Transactional(readOnly = true)
	public Iterable<Account> findAllByStatus(String enableStatus) {
		return accountDao.findAllByStatus(enableStatus);
	}

	@Transactional
	public void saveAccount(Account account, Boolean update) {
		if (account.status == null || "".equals(account.status)) {
			account.status = "有效";
		}
		if (!update) { // 密码单独修改，不随对象变化
			account.password = hashPassword(account.password);
		} else if (account.roleList != null) {
			List<Role> roles = account.roleList;
			for (Role role : roles) { // delete remain role;
				if (role.id < 0) {
					account.roleList.remove(role);
					break;
				}
			}
		}

		accountDao.save(account);

	}

	public boolean checkPassword(String oldPassword, Long id) {
		if(id == -8L) {
			return getCurrentPassword().equals(hashPassword(oldPassword));					
		}
		return true;
	}
	
	@Transactional
	public void updatePassword(String password, Long id) {
		if(id == -8L){
			id = getCurrentUserId();
		}

		//fuck: accountDao.updatePasswordById(hashPassword(password), id);
		Account account = accountDao.findOne(id);
		account.password = hashPassword(password);
		accountDao.save(account);
		
	}

	public boolean existsByCode(String code){
		return accountDao.existsByCode(code) > 0;
	}

	@Transactional(readOnly = true)
	public Account findOne(Long id) {
		if(id == -8L){
			id = this.getCurrentUserId();
		}
		return accountDao.findOne(id);
	}

	/**
	 * 按登录名(工号)查询用户.
	 */
	@Transactional(readOnly = true)
	public Account findUserByLoginName(String code) {
		Account account = accountDao.findByCode(code);
		// if (account != null) {
		// Hibernate.initialize(account.roleList);
		// }
		return account;
	}

	@Transactional
	public void deleteAccount(Long id) {
		accountDao.delete(id);
	}

	/**
	 * 查询用户, 并在返回前对用户的延迟加载关联角色进行初始化.
	 */
	@Transactional(readOnly = true)
	public Account findUserByIdInitialized(Long id) {
		Account account = accountDao.findOne(id);
		if (account != null) {
			Hibernate.initialize(account.roleList);
		}
		return account;
	}
	
	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	public String getCurrentUserName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user == null ? "未登陆" : user.name;
	}

	public Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user == null ? -1L : user.id;
	}
	
	public String getCurrentPassword() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user == null ? "" : user.password;
	}



}
