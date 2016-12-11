package net.bobstudio.so.repository;

import net.bobstudio.so.domain.Customer;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerDao  extends PagingAndSortingRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

	Iterable<Customer> findAllByStatus(String status);


}
