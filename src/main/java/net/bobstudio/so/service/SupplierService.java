package net.bobstudio.so.service;

import net.bobstudio.so.domain.Supplier;
import net.bobstudio.so.repository.SupplierDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierService {
	@Autowired
	private SupplierDao supplierDao;

	@Transactional(readOnly = true)
	public Iterable<Supplier> findSuppliers() {
		return supplierDao.findAll();
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
