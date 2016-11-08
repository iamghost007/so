package net.bobstudio.so.api;

import net.bobstudio.so.domain.Account;
import net.bobstudio.so.domain.Message;
import net.bobstudio.so.domain.Plan;
import net.bobstudio.so.dto.MessageVo;
import net.bobstudio.so.dto.PlanStatus;
import net.bobstudio.so.dto.PlanVo;
import net.bobstudio.so.service.AccountService;
import net.bobstudio.so.service.PlanService;
import net.bobstudio.so.service.exception.ErrorCode;
import net.bobstudio.so.service.exception.ServiceException;

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

@RestController
public class PlanEndPoint {

	@Autowired
	private PlanService planService;

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/api/plans/{id}", produces = MediaTypes.JSON_UTF_8)
	public PlanVo getOnePlan(@PathVariable("id") Long id, Model model) {
		Plan plan = planService.findOne(id);
		
//		Iterable<Message> messages = planService.findMessagesByLink(plan);
//		model.addAttribute("messages", BeanMapper.mapList(messages,MessageVo.class));

		PlanVo pvo = BeanMapper.map(plan, PlanVo.class);
		return pvo;
	}

	// @RequiresPermissions("plan:edit")
	@RequestMapping(value = "/api/plans/{doing}create", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	public PlanVo createPlan(@PathVariable("doing") String doing, @RequestBody PlanVo planVo, UriComponentsBuilder uriBuilder) {
		Plan plan = BeanMapper.map(planVo, Plan.class);
		if (plan.id == null) {
			plan.status = PlanStatus.APPROVE_ORDER.toString();
			Long accountId = accountService.getCurrentUserId();
			if (accountId == -1L) {
				throw new ServiceException("User had session out. Please login again. ",
				        ErrorCode.NO_TOKEN);
			}
			plan.sponsor = new Account(accountId);
		}

		planService.savePlan(plan);

		planService.recordProcess(doing,plan);

		return BeanMapper.map(plan, PlanVo.class);
	}

	// @RequiresPermissions("plan:edit")
	@RequestMapping(value = "/api/plans/{id}/delete")
	public void deletePlan(@PathVariable("id") Long id) {
		planService.deletePlan(id);
	}

}
