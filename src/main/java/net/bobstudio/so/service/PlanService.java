package net.bobstudio.so.service;

import net.bobstudio.so.domain.Account;
import net.bobstudio.so.domain.Message;
import net.bobstudio.so.domain.Plan;
import net.bobstudio.so.dto.PageVo;
import net.bobstudio.so.repository.MessageDao;
import net.bobstudio.so.repository.PlanDao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

@Service
public class PlanService {
	@Autowired
	private PlanDao planDao;

	@Transactional(readOnly = true)
	public Iterable<Plan> findPlans() {
		return planDao.findAll();
	}

	@Transactional(readOnly = true)
	public Iterable<Plan> findPlansBySponsor(Long sponsorId) {
		return planDao.findAllBySponsor(new Account(sponsorId));
	}

	@Transactional(readOnly = true)
	public Page<Plan> findPlansBySponsor(Long sponsorId, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = PageVo.buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Plan> spec = buildSpecification(sponsorId,searchParams);

		return planDao.findAll(spec, pageRequest); 
	}

	private Specification<Plan> buildSpecification(Long sponsorId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("sponsor.id", new SearchFilter("sponsor.id", Operator.EQ, sponsorId));
		Specification<Plan> spec = DynamicSpecifications.bySearchFilter(filters.values(), Plan.class);
		return spec;
	}

	@Transactional(readOnly = true)
	public Iterable<Plan> findAllInProcessing(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		//"from Plan plan where plan.status!='PLAN_OVER'"
		filters.put("status", new SearchFilter("status", Operator.NE, "PLAN_OVER"));
		Specification<Plan> spec = DynamicSpecifications.bySearchFilter(filters.values(), Plan.class);
		
		return planDao.findAll(spec);
	}

	@Transactional(readOnly = true)
	public Iterable<Plan> findAllInProcessing() {
		return planDao.findAllInProcessing();
	}

	@Transactional(readOnly = true)
	public Iterable<Plan> findPlansByStatus(String status) {
		return planDao.findAllByStatus(status);
	}

	@Transactional
	public void savePlan(Plan plan) {
		planDao.save(plan);

	}

	@Transactional(readOnly = true)
	public Plan findOne(Long id) {
		return planDao.findOne(id);
	}

	@Transactional
	public void deletePlan(Long id) {
		planDao.delete(id);

	}
	
	@Transactional
	public void deleteProductsById(Plan plan) {
		planDao.deleteProductsById(plan);
		
	}

	@Transactional
	public void workflow(Plan plan) {
		planDao.updateStatusById(plan.status, plan.id);
	}

	@Autowired
	private MessageDao messageDao;

	@Transactional
	public void recordProcess(String content, Plan plan) {
		Message msg = new Message();
		msg.sender = plan.sponsor;
		msg.plan = plan;
		msg.status = plan.status;
		msg.content = content;

		messageDao.save(msg);
	}


//	 @Transactional
//	 public Iterable<Message> findMessagesByPlan(Long id) {
//		 return messageDao.findAllByLink(id);
//	 }

}
