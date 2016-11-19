package net.bobstudio.so.service;

import net.bobstudio.so.domain.Customer;
import net.bobstudio.so.repository.CustomerDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
