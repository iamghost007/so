/**
 * 
 */
package net.bobstudio.so.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bobstudio.so.domain.Material;
import net.bobstudio.so.domain.MaterialInstock;
import net.bobstudio.so.domain.MaterialOutstock;
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
