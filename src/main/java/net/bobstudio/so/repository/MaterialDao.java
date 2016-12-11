/**
 * 
 */
package net.bobstudio.so.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import net.bobstudio.so.domain.Material;

/**
 * @author Walter <zhanggx2003@126.com>
 * 2016年9月29日
 */
public interface MaterialDao extends PagingAndSortingRepository<Material, Long>, JpaSpecificationExecutor<Material> {

}
