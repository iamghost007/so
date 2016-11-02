package net.bobstudio.so.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;

import net.bobstudio.so.dto.PlanVo;

/**
 * 
 * @author Bob Zhang[zzb205@163.com] 2016.9.26
 */
@Controller
@RequestMapping("/")
public class PlanController {

	@GetMapping("plans/main")
	public String list(@ModelAttribute PlanVo planVo) {
		//Iterable<Plan> roles = planService.findRoles();
		//return new ModelAndView("plans/planList");
		return ("plans/planList");
	}

}
