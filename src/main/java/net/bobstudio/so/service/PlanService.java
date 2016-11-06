package net.bobstudio.so.service;

import net.bobstudio.so.domain.Plan;
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

}
