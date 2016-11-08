package net.bobstudio.so.service;

import net.bobstudio.so.domain.Message;
import net.bobstudio.so.domain.Plan;
import net.bobstudio.so.repository.MessageDao;
import net.bobstudio.so.repository.PlanDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return planDao.findAllBySponsor(sponsorId);
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

	@Autowired
	private MessageDao messageDao;

	@Transactional
	public void recordProcess(String doing, Plan plan) {
	    Message msg = new Message();
	    msg.sender = plan.sponsor;
	    msg.plan = plan;
	    msg.status = plan.status;
	    msg.content = doing;
	    
	    messageDao.save(msg);
    }
	
//	@Transactional
//	public Iterable<Message> findMessagesByLink(Plan plan) {
//		return messageDao.findAllByLink(plan);
//	}

}
