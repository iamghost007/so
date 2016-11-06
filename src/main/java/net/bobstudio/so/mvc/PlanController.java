package net.bobstudio.so.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.mapper.BeanMapper;

import net.bobstudio.so.domain.Plan;
import net.bobstudio.so.dto.PlanVo;
import net.bobstudio.so.service.PlanService;

/**
 * 
 * @author Bob Zhang[zzb205@163.com] 2016.9.26
 */
@Controller
@RequestMapping("/pans")
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
	@GetMapping("main")
	public ModelAndView list(@ModelAttribute PlanVo planVo) {
		Iterable<Plan> plans = planService.findPlans();
		return new ModelAndView("plans/planList","plans", BeanMapper.mapList(plans, PlanVo.class));
	}
}
