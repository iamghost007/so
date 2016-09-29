/**
 * 
 */
package net.bobstudio.so.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bobstudio.so.domain.Drawing;
import net.bobstudio.so.repository.DrawingDao;

/**
 * @author Bob Zhang[zzb205@163.com]
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

}
