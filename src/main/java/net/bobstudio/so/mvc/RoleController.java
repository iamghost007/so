package net.bobstudio.so.mvc;

import net.bobstudio.so.domain.Role;
import net.bobstudio.so.dto.PageVo;
import net.bobstudio.so.dto.RoleVo;
import net.bobstudio.so.service.RoleService;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.mapper.BeanMapper;
import org.springside.modules.web.Servlets;

/**
 * 
 * @author Bob Zhang[zzb205@163.com] 2016.9.26
 */
@Controller
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("main")
	public ModelAndView list(@ModelAttribute RoleVo roleVo,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PageVo.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PageVo.SEARCH_PERFIX);
		
		Page<Role> roles = roleService.findRoles(searchParams, pageNumber, pageSize, sortType);
		
		PageModel.setModelForPage(sortType, model, searchParams, new PageVo("/roles", roles));
		
		return new ModelAndView("accounts/roleList", "roles", BeanMapper.mapList(roles.getContent(), RoleVo.class));
	}

}
