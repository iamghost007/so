/**
 * 
 */
package net.bobstudio.so.repository;

import net.bobstudio.so.domain.Product;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Bob Zhang[zzb205@163.com]
 * 2016年9月29日
 */
public interface ProductDao extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	
	@Query("from Product product where product.status=:status order by product.code asc ")
	Iterable<Product> findAllByStatus(@Param("status")String status);

}
