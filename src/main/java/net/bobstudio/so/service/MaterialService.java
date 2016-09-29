/**
 * 
 */
package net.bobstudio.so.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bobstudio.so.domain.Material;
import net.bobstudio.so.repository.MaterialDao;

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

}
