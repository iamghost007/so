package net.bobstudio.so.repository;

import net.bobstudio.so.domain.Supplier;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SupplierDao  extends PagingAndSortingRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier> {

	Iterable<Supplier> findAllByStatus(String status);


}
