/**
 * 
 */
package net.bobstudio.so.repository;

import net.bobstudio.so.domain.Product;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Bob Zhang[zzb205@163.com]
 * 2016年9月29日
 */
public interface ProductDao extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	
	Iterable<Product> findAllByStatus(String status);

}
