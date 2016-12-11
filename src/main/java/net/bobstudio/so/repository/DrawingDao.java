/**
 * 
 */
package net.bobstudio.so.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import net.bobstudio.so.domain.Drawing;

/**
 * @author Walter <zhanggx2003@126.com>
 * 2016年9月29日
 */
public interface DrawingDao extends PagingAndSortingRepository<Drawing, Long>, JpaSpecificationExecutor<Drawing> {

}
