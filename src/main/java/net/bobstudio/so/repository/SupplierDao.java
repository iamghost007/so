package net.bobstudio.so.repository;

import net.bobstudio.so.domain.Supplier;

import org.springframework.data.repository.CrudRepository;

public interface SupplierDao  extends CrudRepository<Supplier, Long>{

	Iterable<Supplier> findAllByStatus(String status);


}
