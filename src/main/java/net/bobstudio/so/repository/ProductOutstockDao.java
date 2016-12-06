package net.bobstudio.so.repository;

import net.bobstudio.so.domain.ProductOutstock;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Bob Zhang[zzb205@163.com]
 * 2016年9月29日
 */
public interface ProductOutstockDao extends PagingAndSortingRepository<ProductOutstock, Long>, JpaSpecificationExecutor<ProductOutstock> {

}
