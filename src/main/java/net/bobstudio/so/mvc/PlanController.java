package net.bobstudio.so.mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
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

import net.bobstudio.so.domain.Account;
import net.bobstudio.so.domain.Customer;
import net.bobstudio.so.domain.Plan;
import net.bobstudio.so.domain.Product;
import net.bobstudio.so.dto.AccountVo;
import net.bobstudio.so.dto.OrderType;
import net.bobstudio.so.dto.PageVo;
import net.bobstudio.so.dto.PlanVo;
import net.bobstudio.so.dto.ProductVo;
import net.bobstudio.so.dto.Status;
import net.bobstudio.so.service.AccountService;
import net.bobstudio.so.service.CustomerService;
import net.bobstudio.so.service.PlanService;
import net.bobstudio.so.service.ProductService;

import static net.bobstudio.so.dto.PlanStatus.*;

/**
 * 
 * @author Bob Zhang[zzb205@163.com] 2016.9.26
 */
@Controller
@RequestMapping("/plans")
public class PlanController {

	@Autowired
	private PlanService planService;

	@Autowired
	private ProductService productService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private CustomerService customerService;

	private static final String ENABLE_STATUS = Status.ENABLE.getDescription();

	@GetMapping("main")
	public ModelAndView list(@ModelAttribute PlanVo planVo, Model model) {

		Iterable<Plan> plans = filterMine(planService.findAllInProcessing());
		setModelForPlan(model);

		return new ModelAndView("plans/planList", "plans", BeanMapper.mapList(plans, PlanVo.class));
	}

	private Iterable<Plan> filterMine(Iterable<Plan> plans) {
		List<Plan> myPlans = new ArrayList<Plan>();
		for (Plan plan : plans) {
			if (plan.sponsor != null && processorIstMe(plan.status, plan.sponsor.id)) {
				myPlans.add(plan);
			}

		}
		return myPlans;
	}

	private boolean processorIstMe(String status, Long sponsorId) {
		// 仓储处理，这里只读
		if (status.equals(PULL_MATERIAL.toString()) || status.equals(PUSH_PRODUCT.toString())
				|| status.equals(PULL_PRODUCT.toString())) {
			return true;
		}
		// 本人的重拟
		else if (status.endsWith(REVIEW_ORDER.toString()) && sponsorId == accountService.getCurrentUserId()) {
			return true;
		}
		// 有权审核
		else if (status.endsWith(APPROVE_ORDER.toString())) {
			return SecurityUtils.getSubject().isPermitted("plan:audit");
		}
		// 有权转产
		else if (status.endsWith(TO_PRODUCT.toString())) {
			return SecurityUtils.getSubject().isPermitted("plan:plan");
		}

		return false;
	}

	@GetMapping("sponsor_is_me")
	public ModelAndView sponsorlist(@ModelAttribute PlanVo planVo,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PageVo.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Long myid = accountService.getCurrentUserId();
		if (myid == -1L) { // session过期了
			return new ModelAndView("plans/sponsorIsMe", "plans", null);
		}

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<Plan> plans = planService.findPlansBySponsor(myid, searchParams, pageNumber, pageSize, sortType);
		PageModel.setModelForPage(sortType, model, new PageVo("/plans", "/sponsor_is_me", plans));
		setModelForPlan(model);

		return new ModelAndView("plans/sponsorIsMe", "plans", BeanMapper.mapList(plans.getContent(), PlanVo.class));
	}

	private void setModelForPlan(Model model) {
		model.addAttribute("allOrderType", Arrays.asList(OrderType.values()));

		Iterable<Product> products = productService.findAllByStatus(ENABLE_STATUS);
		model.addAttribute("products", BeanMapper.mapList(products, ProductVo.class));

		Iterable<Account> salesmen = accountService.findAllByStatus(ENABLE_STATUS);
		model.addAttribute("salesmen", BeanMapper.mapList(salesmen, AccountVo.class));

		Iterable<Customer> customers = customerService.findAllByStatus(ENABLE_STATUS);
		model.addAttribute("customers", BeanMapper.mapList(customers, AccountVo.class));

	}

}
