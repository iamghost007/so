package net.bobstudio.so.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.mapper.BeanMapper;

import com.google.common.collect.Lists;

import net.bobstudio.so.domain.Message;
import net.bobstudio.so.domain.Plan;
import net.bobstudio.so.dto.MessageVo;
import net.bobstudio.so.dto.PlanVo;
import net.bobstudio.so.service.PlanService;

/**
 * 
 * @author Bob Zhang[zzb205@163.com] 2016.9.26
 */
@Controller
@RequestMapping("/plans")
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
	@GetMapping("main")
	public ModelAndView list(@ModelAttribute PlanVo planVo) {
		Iterable<Plan> plans = planService.findPlans();
		return new ModelAndView("plans/planList","plans", BeanMapper.mapList(plans, PlanVo.class));
	}
	
	@GetMapping("{planId}/bpmn")
	public ModelAndView view(@PathVariable("planId") Long id) {
		Plan plan = planService.findOne(id);
		Iterable<Message> messages = Lists.newArrayList();
		
		if(plan != null) {
			messages = plan.messages;
		}
				
		return new ModelAndView("plans/planBpmn","messages", BeanMapper.mapList(messages, MessageVo.class));
	}
}
