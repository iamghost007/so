package net.bobstudio.so.service;

import net.bobstudio.so.domain.Customer;
import net.bobstudio.so.dto.PageVo;
import net.bobstudio.so.repository.CustomerDao;

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
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;

	@Transactional(readOnly = true)
	public Iterable<Customer> findAll() {
		return customerDao.findAll();
	}

	@Transactional(readOnly = true)
	public Iterable<Customer> findAllByStatus(String status) {
		return customerDao.findAllByStatus(status);
	}

	@Transactional(readOnly = true)
	public Page<Customer> findAll(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = PageVo.buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Customer> spec = buildSpecification(searchParams);

		return customerDao.findAll(spec, pageRequest); 
	}

	private Specification<Customer> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		//filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<Customer> spec = DynamicSpecifications.bySearchFilter(filters.values(), Customer.class);
		return spec;
	}

	@Transactional
	public void saveCustomer(Customer customer) {
		customerDao.save(customer);

	}

	@Transactional(readOnly = true)
	public Customer findOne(Long id) {
		return customerDao.findOne(id);
	}

	@Transactional
	public void deleteCustomer(Long id) {
		customerDao.delete(id);

	}

}
