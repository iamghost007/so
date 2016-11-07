package net.bobstudio.so.repository;

import net.bobstudio.so.domain.Plan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlanDao  extends CrudRepository<Plan, Long>{

	//@Query("select from Plan where sponsor=?")
	Iterable<Plan> findAllBySponsor(Long sponsorId);

	Iterable<Plan> findAllByStatus(String status);

}
