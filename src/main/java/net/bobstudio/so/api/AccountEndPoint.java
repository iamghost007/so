package net.bobstudio.so.api;

import java.util.Collections;
import java.util.Map;

import net.bobstudio.so.domain.Account;
import net.bobstudio.so.dto.AccountVo;
import net.bobstudio.so.service.AccountService;
import net.bobstudio.so.service.exception.ErrorCode;
import net.bobstudio.so.service.exception.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springside.modules.constants.MediaTypes;
import org.springside.modules.mapper.BeanMapper;

// Spring Restful MVC Controller的标识, 直接输出内容，不调用template引擎.
@RestController
public class AccountEndPoint {

	//private static Logger logger = LoggerFactory.getLogger(AccountEndPoint.class);

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/api/accounts/login", produces = MediaTypes.JSON_UTF_8)
	public Map<String, String> login(@RequestParam("email") String email,
	        @RequestParam("password") String password) {

		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
			throw new ServiceException("User or password empty", ErrorCode.BAD_REQUEST);
		}

		String token = accountService.login(email, password);

		return Collections.singletonMap("token", token);
	}

	@RequestMapping(value = "/api/accounts/logout")
	public void logout(@RequestParam(value = "token", required = false) String token) {
		accountService.logout(token);
	}

	@RequestMapping(value = "/api/accounts/register")
	public void register(@RequestParam("email") String email,
	        @RequestParam(value = "name", required = false) String name,
	        @RequestParam("password") String password) {

		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(name)
		        || StringUtils.isEmpty(password)) {
			throw new ServiceException("User or name or password empty", ErrorCode.BAD_REQUEST);
		}

		accountService.register(email, name, password);
	}
	
	@RequestMapping(value = "/api/accounts/create", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	public AccountVo createAccount(@RequestBody AccountVo accountVo,
	        UriComponentsBuilder uriBuilder) {
		Account account = BeanMapper.map(accountVo, Account.class);
		accountService.saveAccount(account);

//		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
//		URI uri = uriBuilder.path("/products/accountsList").build().toUri();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(uri);
//
//		return new ResponseEntity<AccountVo>(headers, HttpStatus.CREATED);
		
		return BeanMapper.map(account, AccountVo.class);
	}

	@RequestMapping(value = "/api/accounts/{id}", produces = MediaTypes.JSON_UTF_8)
	public AccountVo listOneAccount(@PathVariable("id") Long id) {
		Account account = accountService.findOne(id);

		return BeanMapper.map(account, AccountVo.class);
	}

	@RequestMapping(value = "/api/accounts/{id}/delete")
	public void deleteAccount(@PathVariable("id") Long id) {
		accountService.deleteAccount(id);
	}

}
