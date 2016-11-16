package net.bobstudio.so.repository;

import net.bobstudio.so.domain.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerDao  extends CrudRepository<Customer, Long>{

	Iterable<Customer> findAllByStatus(String status);


}
