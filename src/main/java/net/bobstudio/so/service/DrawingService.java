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

import net.bobstudio.so.domain.Drawing;
import net.bobstudio.so.dto.PageVo;
import net.bobstudio.so.repository.DrawingDao;

/**
 * @author Walter <zhanggx2003@126.com>
 * 2016年9月29日
 */
@Service
public class DrawingService {
	@Autowired
	private DrawingDao drawingDao;


	@Transactional(readOnly = true)
	public Iterable<Drawing> findAll() {
		return drawingDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<Drawing> findAll(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = PageVo.buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Drawing> spec = buildSpecification(searchParams);

		return drawingDao.findAll(spec, pageRequest); 
	}

	private Specification<Drawing> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		//filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<Drawing> spec = DynamicSpecifications.bySearchFilter(filters.values(), Drawing.class);
		return spec;
	}

	@Transactional
	public void saveDrawing(Drawing drawing) {
		drawingDao.save(drawing);
	}
	
	@Transactional(readOnly = true)
	public Drawing findOne(Long id) {
		return drawingDao.findOne(id);
	}
	
	@Transactional
	public void deleteDrawing(Long id){
		drawingDao.delete(id);
	}

}
