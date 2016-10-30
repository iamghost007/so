package net.bobstudio.so.mvc;

import net.bobstudio.so.domain.Role;
import net.bobstudio.so.dto.RoleVo;
import net.bobstudio.so.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.mapper.BeanMapper;

/**
 * 
 * @author Bob Zhang[zzb205@163.com] 2016.9.26
 */
@Controller
@RequestMapping("/")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("roles/main")
	public ModelAndView list(@ModelAttribute RoleVo roleVo) {
		Iterable<Role> roles = roleService.findRoles();
		return new ModelAndView("accounts/roleList", "roles", BeanMapper.mapList(roles, RoleVo.class));
	}

}
