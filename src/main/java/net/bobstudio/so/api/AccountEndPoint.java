package net.bobstudio.so.api;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.bobstudio.so.domain.Account;
import net.bobstudio.so.domain.Role;
import net.bobstudio.so.dto.AccountVo;
import net.bobstudio.so.dto.RoleVo;
import net.bobstudio.so.service.AccountService;
import net.bobstudio.so.service.RoleService;
import net.bobstudio.so.service.exception.ErrorCode;
import net.bobstudio.so.service.exception.ServiceException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/api/accounts/{id}", produces = MediaTypes.JSON_UTF_8)
	public AccountVo getOneAccount(@PathVariable("id") Long id) {
		Account account = accountService.findOne(id);

		AccountVo vo = BeanMapper.map(account, AccountVo.class);
		return vo;
	}

	@RequestMapping(value = "/api/account_roles/{id}", produces = MediaTypes.JSON_UTF_8)
	public AccountVo getOneAccountRoles(@PathVariable("id") Long id) {
		Account account = accountService.findUserByIdInitialized(id);

		return BeanMapper.map(account, AccountVo.class);
	}

	//@RequiresPermissions("account:edit")
	@RequestMapping(value = "/api/accounts/create", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	public AccountVo createAccount(@RequestBody AccountVo accountVo) {
		Account account = BeanMapper.map(accountVo, Account.class);
		accountService.saveAccount(account, accountVo.getUpdate());

		return BeanMapper.map(account, AccountVo.class);
	}

	//@RequiresPermissions("account:edit")
	@RequestMapping(value = "/api/accounts/{id}/delete")
	public void deleteAccount(@PathVariable("id") Long id) {
		accountService.deleteAccount(id);
	}

	//@RequiresRoles("分配角色")
	@RequestMapping(value = "/api/accounts/set_roles/{user_id}/{ids}", produces = MediaTypes.JSON_UTF_8)
	public void setRolesForOneAccount(@PathVariable("user_id") Long userId,
	        @PathVariable("ids") String ids) {
		String[] role_ids = ids.split(",");

		Account account = accountService.findUserByIdInitialized(userId);
		account.roleList.clear();
		for (String roleId : role_ids) {
			Role role = new Role(new Long(roleId));
			account.roleList.add(role);
		}
		accountService.saveAccount(account, true);

	}

	@RequestMapping(value = "/api/accounts/login", produces = MediaTypes.JSON_UTF_8)
	public Map<String, String> login(@RequestParam("code") String code,
	        @RequestParam("password") String password) {

		if (StringUtils.isEmpty(code) || StringUtils.isEmpty(password)) {
			throw new ServiceException("User code or password empty", ErrorCode.BAD_REQUEST);
		}
		
		//TODO rest远程校验登陆与身份合法性，仅支持仓储身份

		String token = accountService.login(code, password);

		return Collections.singletonMap("token", token);
	}

	// ///////////////////// ROLE Start/////////////////////////
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/api/roles/{id}", produces = MediaTypes.JSON_UTF_8)
	public RoleVo getOneRole(@PathVariable("id") Long id) {
		Role role = roleService.findOne(id);

		return BeanMapper.map(role, RoleVo.class);
	}

	@RequestMapping(value = "/api/roles/tree/{user_id}", produces = MediaTypes.JSON_UTF_8)
	public List<RoleVo> getRoleTree(@PathVariable("user_id") Long userId) {
		Iterable<Role> allRoles = roleService.findRoleTree();
		List<Role> currentRoles = accountService.findUserByIdInitialized(userId).roleList;

		List<RoleVo> tree = BeanMapper.mapList(allRoles, RoleVo.class);
		if (currentRoles != null && currentRoles.size() > 0) {
			for (RoleVo role : tree) {
				for (Role existRole : currentRoles) {
					if (existRole.id == role.getId()) {
						role.setChecked(true);
					}
				}
			}
		}

		return tree;
	}

	//@RequiresPermissions("role:edit")
	@RequestMapping(value = "/api/roles/create", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	public RoleVo createRole(@RequestBody RoleVo roleVo, UriComponentsBuilder uriBuilder) {
		Role role = BeanMapper.map(roleVo, Role.class);
		roleService.saveRole(role);

		return BeanMapper.map(role, RoleVo.class);
	}

	//@RequiresPermissions("role:edit")
	@RequestMapping(value = "/api/roles/{id}/delete")
	public void deleteRole(@PathVariable("id") Long id) {
		roleService.deleteRole(id);
	}

	// /////////////////////////////////////////////////////
}
