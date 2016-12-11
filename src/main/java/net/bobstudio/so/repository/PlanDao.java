package net.bobstudio.so.repository;

import net.bobstudio.so.domain.Account;
import net.bobstudio.so.domain.Plan;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PlanDao extends PagingAndSortingRepository<Plan, Long>, JpaSpecificationExecutor<Plan> {

	@Query("from Plan plan where plan.sponsor=:sponsor")
	Iterable<Plan> findAllBySponsor(@Param("sponsor")Account sponsor);

	Iterable<Plan> findAllByStatus(String status);
	
	@Query("from Plan plan where plan.status!='PLAN_OVER'")
	Iterable<Plan> findAllInProcessing();

	@Modifying
	@Query("update Plan p set p.status=:status where p.id=:id")
	void updateStatusById(@Param("status")String status, @Param("id")Long id);

}
