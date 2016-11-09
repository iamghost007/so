package net.bobstudio.so.repository;

import net.bobstudio.so.domain.Plan;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PlanDao  extends CrudRepository<Plan, Long>{

	//@Query("select from Plan where sponsor=?")
	Iterable<Plan> findAllBySponsor(Long sponsorId);

	Iterable<Plan> findAllByStatus(String status);

	@Modifying
	@Query("update Plan p set p.status=:status where p.id=:id")
	void updateStatusById(@Param("status")String status, @Param("id")Long id);

}
