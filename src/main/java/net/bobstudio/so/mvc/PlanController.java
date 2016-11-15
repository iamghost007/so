package net.bobstudio.so.mvc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.mapper.BeanMapper;

import net.bobstudio.so.domain.Plan;
import net.bobstudio.so.domain.Product;
import net.bobstudio.so.dto.OrderType;
import net.bobstudio.so.dto.PlanVo;
import net.bobstudio.so.dto.ProductVo;
import net.bobstudio.so.service.PlanService;
import net.bobstudio.so.service.ProductService;

/**
 * 
 * @author Bob Zhang[zzb205@163.com] 2016.9.26
 */
@Controller
@RequestMapping("/plans")
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
	private ProductService productService;
	
	@GetMapping("main")
	public ModelAndView list(@ModelAttribute PlanVo planVo, Model model) {
		Iterable<Plan> plans = planService.findPlans();
		model.addAttribute("allOrderType", Arrays.asList(OrderType.SALE,OrderType.PRODUCTION));
		Iterable<Product> products = productService.findAll();
		model.addAttribute("products", BeanMapper.mapList(products, ProductVo.class));
		
		return new ModelAndView("plans/planList","plans", BeanMapper.mapList(plans, PlanVo.class));
	}
	
//	@GetMapping("{planId}/bpmn")
//	public ModelAndView view(@PathVariable("planId") Long id) {
//		Plan plan = planService.findOne(id);
//		Iterable<Message> messages = Lists.newArrayList();
//		
//		if(plan != null) {
//			messages = plan.messages;
//		}
//				
//		return new ModelAndView("plans/planBpmn","messages", BeanMapper.mapList(messages, MessageVo.class));
//	}
}
