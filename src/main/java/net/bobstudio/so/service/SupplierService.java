package net.bobstudio.so.service;

import net.bobstudio.so.domain.Supplier;
import net.bobstudio.so.dto.PageVo;
import net.bobstudio.so.repository.SupplierDao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

@Service
public class SupplierService {
	@Autowired
	private SupplierDao supplierDao;

	@Transactional(readOnly = true)
	public Iterable<Supplier> findSuppliers() {
		return supplierDao.findAll();
	}

	@Transactional(readOnly = true)
	public Page<Supplier> findSuppliers(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = PageVo.buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Supplier> spec = buildSpecification(searchParams);

		return supplierDao.findAll(spec, pageRequest); 
	}

	private Specification<Supplier> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		//filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<Supplier> spec = DynamicSpecifications.bySearchFilter(filters.values(), Supplier.class);
		return spec;
	}

	@Transactional(readOnly = true)
	public Iterable<Supplier> findSuppliersByStatus(String status) {
		return supplierDao.findAllByStatus(status);
	}

	@Transactional
	public void saveSupplier(Supplier supplier) {
		supplierDao.save(supplier);

	}

	@Transactional(readOnly = true)
	public Supplier findOne(Long id) {
		return supplierDao.findOne(id);
	}

	@Transactional
	public void deleteSupplier(Long id) {
		supplierDao.delete(id);

	}

}
