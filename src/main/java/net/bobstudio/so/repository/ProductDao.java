/**
 * 
 */
package net.bobstudio.so.repository;

import net.bobstudio.so.domain.Product;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Bob Zhang[zzb205@163.com]
 * 2016年9月29日
 */
public interface ProductDao extends CrudRepository<Product, Long> {

}
