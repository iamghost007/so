package net.bobstudio.so.api;

import net.bobstudio.so.domain.Account;
import net.bobstudio.so.domain.Message;
import net.bobstudio.so.domain.Plan;
import net.bobstudio.so.domain.ProductInPlan;
import net.bobstudio.so.dto.MessageVo;
import net.bobstudio.so.dto.PlanBatchToProduct;
import net.bobstudio.so.dto.PlanStatus;
import net.bobstudio.so.dto.PlanVo;
import net.bobstudio.so.service.AccountService;
import net.bobstudio.so.service.PlanService;
import net.bobstudio.so.service.exception.ErrorCode;
import net.bobstudio.so.service.exception.ServiceException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springside.modules.constants.MediaTypes;
import org.springside.modules.mapper.BeanMapper;

import com.google.common.collect.Lists;

@RestController
public class PlanEndPoint {

	@Autowired
	private PlanService planService;

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/api/plans/{id}", produces = MediaTypes.JSON_UTF_8)
	public PlanVo getOnePlan(@PathVariable("id") Long id, Model model) {
		Plan plan = planService.findOne(id);

		// Iterable<Message> messages = planService.findMessagesByLink(plan);
		// model.addAttribute("messages",
		// BeanMapper.mapList(messages,MessageVo.class));

		PlanVo pvo = BeanMapper.map(plan, PlanVo.class);
		return pvo;
	}

	// @RequiresPermissions("plan:edit")
	@RequestMapping(value = "/api/plans/create", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	public PlanVo createPlan(@RequestBody PlanVo planVo, UriComponentsBuilder uriBuilder) {
		Plan plan = BeanMapper.map(planVo, Plan.class);
		plan.sponsor = getCurrentAccount();
		// 起草或重拟
		if (PlanStatus.DRIFTING.toString().equals(planVo.getContent())
				|| PlanStatus.REVIEW_ORDER.toString().equals(planVo.getContent())) {
			if (plan.id == null) {
				plan.status = PlanStatus.APPROVE_ORDER.toString();
			}
			else { //delete current products
				planService.deleteProductsById(plan);
			}
			for(ProductInPlan planProduct : plan.planProducts) {
				planProduct.plan = plan;
			}
			planService.savePlan(plan);
		} else {
			planService.workflow(plan);
		}

		planService.recordProcess(planVo.getContent(), plan);

		return BeanMapper.map(plan, PlanVo.class);
	}

	private Account getCurrentAccount() {
		Long accountId = accountService.getCurrentUserId();
		if (accountId == -1L) {
			throw new ServiceException("User had session out. Please login again. ", ErrorCode.NO_TOKEN);
		}
		return new Account(accountId);

	}

	@RequestMapping(value = "/api/plans/save/batch_to_product", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	public void workflow(@RequestBody PlanBatchToProduct batch) {

		for (String id : batch.getPlanIds()) {
			String [] ids = id.split(",");
			Plan plan = new Plan(Long.valueOf(ids[0]), batch.getStatus());
			plan.sponsor = new Account(Long.valueOf(ids[1]));
			planService.workflow(plan);
			planService.recordProcess(batch.getContent(), plan);
		}

	}

	// @RequiresPermissions("plan:edit")
	@RequestMapping(value = "/api/plans/{id}/delete")
	public void deletePlan(@PathVariable("id") Long id) {
		planService.deletePlan(id);
	}

	@RequestMapping("/api/plans/batch_to_product")
	public Iterable<PlanVo> batchToProduct() {
		Iterable<Plan> plans = planService.findPlansByStatus(PlanStatus.TO_PRODUCT.toString());

		return BeanMapper.mapList(plans, PlanVo.class);
	}

	@RequestMapping("/api/plans/{planId}/bpmn")
	public Iterable<MessageVo> view(@PathVariable("planId") Long id) {
		Plan plan = planService.findOne(id);
		List<Message> messages = Lists.newArrayList();

		if (plan != null) {
			messages = plan.messages;
			Collections.sort(messages, new Comparator<Message>() {
				@Override
				public int compare(Message m1, Message m2) {

					return m2.receiveDate.compareTo(m1.receiveDate);
				}
			});
		}

		return BeanMapper.mapList(messages, MessageVo.class);
	}

}
