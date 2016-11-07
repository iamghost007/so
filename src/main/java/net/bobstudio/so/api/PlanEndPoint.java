package net.bobstudio.so.api;

import net.bobstudio.so.domain.Plan;
import net.bobstudio.so.dto.PlanVo;
import net.bobstudio.so.service.PlanService;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/api/plans/{id}", produces = MediaTypes.JSON_UTF_8)
	public PlanVo getOnePlan(@PathVariable("id") Long id) {
		Plan plan = planService.findOne(id);

		return BeanMapper.map(plan, PlanVo.class);
	}

	//@RequiresPermissions("plan:edit")
	@RequestMapping(value = "/api/plans/create", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	public PlanVo createPlan(@RequestBody PlanVo planVo, UriComponentsBuilder uriBuilder) {
		Plan plan = BeanMapper.map(planVo, Plan.class);
		planService.savePlan(plan);
		
		planService.recordProcess(plan);

		return BeanMapper.map(plan, PlanVo.class);
	}

	//@RequiresPermissions("plan:edit")
	@RequestMapping(value = "/api/plans/{id}/delete")
	public void deletePlan(@PathVariable("id") Long id) {
		planService.deletePlan(id);
	}

}
