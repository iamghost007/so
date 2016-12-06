package net.bobstudio.so.service;

import java.util.Map;

import net.bobstudio.so.domain.Product;
import net.bobstudio.so.domain.ProductInstock;
import net.bobstudio.so.domain.ProductOutstock;
import net.bobstudio.so.dto.PageVo;
import net.bobstudio.so.repository.ProductDao;
import net.bobstudio.so.repository.ProductInstockDao;
import net.bobstudio.so.repository.ProductOutstockDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
//import org.springside.modules.persistence.SearchFilter.Operator;

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
	
	@Transactional(readOnly = true)
	public Iterable<Product> findAllByStatus(String enableStatus) {
		return productDao.findAllByStatus(enableStatus);
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

	/////////////////////////////////////////////////
	@Autowired
	private ProductInstockDao prodInstockDao;

	@Transactional(readOnly = true)
	public Iterable<ProductInstock> findAllInstock() {
		return prodInstockDao.findAll();
	}

	@Transactional(readOnly = true)
	public Page<ProductInstock> findAllInstock(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = PageVo.buildPageRequest(pageNumber, pageSize, sortType);
		Specification<ProductInstock> spec = buildSpecification(searchParams);

		return prodInstockDao.findAll(spec, pageRequest); 
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<ProductInstock> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		//filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<ProductInstock> spec = DynamicSpecifications.bySearchFilter(filters.values(), ProductInstock.class);
		return spec;
	}

	@Transactional
	public void saveProdInstock(ProductInstock prodInstock) {
		prodInstockDao.save(prodInstock);

	}

	@Transactional(readOnly = true)
	public ProductInstock findOneProdInstock(Long id) {
		return prodInstockDao.findOne(id);
	}
	
	@Transactional
	public void deleteProdInstock(Long id){
		prodInstockDao.delete(id);
	}
	
	////////////////////////////////////////////
	@Autowired
	private ProductOutstockDao prodOutstockDao;

	@Transactional(readOnly = true)
	public Iterable<ProductOutstock> findAllOutstock() {
		return prodOutstockDao.findAll();
	}

	@Transactional(readOnly = true)
	public Page<ProductOutstock> findAllOutstock(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = PageVo.buildPageRequest(pageNumber, pageSize, sortType);
		Specification<ProductOutstock> spec = buildOutstockSpecification(searchParams);

		return prodOutstockDao.findAll(spec, pageRequest); 
	}
	
	private Specification<ProductOutstock> buildOutstockSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		//filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<ProductOutstock> spec = DynamicSpecifications.bySearchFilter(filters.values(), ProductOutstock.class);
		return spec;
	}

	@Transactional
	public void saveProdOutstock(ProductOutstock prodOutstock) {
		prodOutstockDao.save(prodOutstock);

	}

	@Transactional(readOnly = true)
	public ProductOutstock findOneProdOutstock(Long id) {
		return prodOutstockDao.findOne(id);
	}
	
	@Transactional
	public void deleteProdOutstock(Long id){
		prodOutstockDao.delete(id);
	}

}
