/**
 * 
 */
package net.bobstudio.so.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import net.bobstudio.so.domain.Material;
import net.bobstudio.so.domain.MaterialInstock;
import net.bobstudio.so.domain.MaterialOutstock;
import net.bobstudio.so.dto.PageVo;
import net.bobstudio.so.repository.MaterialDao;
import net.bobstudio.so.repository.MaterialInstockDao;
import net.bobstudio.so.repository.MaterialOutstockDao;

/**
 * @author Walter <zhanggx2003@126.com>
 * 2016年9月29日
 */
@Service
public class MaterialService {
	@Autowired
	private MaterialDao materialDao;


	@Transactional(readOnly = true)
	public Iterable<Material> findAll() {
		return materialDao.findAll();
	}


	public void saveMaterial(Material material) {
		materialDao.save(material);
		
	}

	@Transactional(readOnly = true)
	public Material findOne(Long id) {
		return materialDao.findOne(id);
	}

	@Transactional
	public void deleteMaterial(Long id) {
		materialDao.delete(id);
		
	}

	/////////////////////////////////////////////////
	@Autowired
	private MaterialInstockDao mateInstockDao;

	@Transactional(readOnly = true)
	public Iterable<MaterialInstock> findAllInstock() {
		return mateInstockDao.findAll();
	}

	@Transactional(readOnly = true)
	public Page<MaterialInstock> findAllInstock(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = PageVo.buildPageRequest(pageNumber, pageSize, sortType);
		Specification<MaterialInstock> spec = buildSpecification(searchParams);

		return mateInstockDao.findAll(spec, pageRequest); 
	}

	private Specification<MaterialInstock> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		//filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<MaterialInstock> spec = DynamicSpecifications.bySearchFilter(filters.values(), MaterialInstock.class);
		return spec;
	}

	@Transactional
	public void saveMaterialInstock(MaterialInstock materialInstock) {
		mateInstockDao.save(materialInstock);

	}

	@Transactional(readOnly = true)
	public MaterialInstock findOneMaterialInstock(Long id) {
		return mateInstockDao.findOne(id);
	}
	
	@Transactional
	public void deleteMaterialInstock(Long id){
		mateInstockDao.delete(id);
	}
	
	////////////////////////////////////////////
	@Autowired
	private MaterialOutstockDao mateOutstockDao;

	@Transactional(readOnly = true)
	public Iterable<MaterialOutstock> findAllOutstock() {
		return mateOutstockDao.findAll();
	}

	@Transactional(readOnly = true)
	public Page<MaterialOutstock> findAllOutstock(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = PageVo.buildPageRequest(pageNumber, pageSize, sortType);
		Specification<MaterialOutstock> spec = buildOutSpecification(searchParams);

		return mateOutstockDao.findAll(spec, pageRequest); 
	}


	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<MaterialOutstock> buildOutSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		//filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<MaterialOutstock> spec = DynamicSpecifications.bySearchFilter(filters.values(), MaterialOutstock.class);
		return spec;
	}

	
	@Transactional
	public void saveMaterialOutstock(MaterialOutstock materialOutstock) {
		mateOutstockDao.save(materialOutstock);

	}

	@Transactional(readOnly = true)
	public MaterialOutstock findOneMaterialOutstock(Long id) {
		return mateOutstockDao.findOne(id);
	}
	
	@Transactional
	public void deleteMaterialOutstock(Long id){
		mateOutstockDao.delete(id);
	}
}
