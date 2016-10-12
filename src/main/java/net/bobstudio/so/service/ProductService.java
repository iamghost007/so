/**
 * 
 */
package net.bobstudio.so.service;

import net.bobstudio.so.domain.Product;
import net.bobstudio.so.repository.ProductDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bob Zhang[zzb205@163.com] 2016年9月29日
 */
@Service
public class ProductService {
	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private ProductDao productDao;

	@Transactional(readOnly = true)
	public Iterable<Product> findAll() {
		return productDao.findAll();
	}

	@Transactional
	public void saveProduct(Product product) {
		productDao.save(product);

	}

	@Transactional(readOnly = true)
	public Product findOne(Long id) {
		return productDao.findOne(id);
	}
	
	@Transactional
	public void deleteProduct(Long id){
		productDao.delete(id);
	}

}
