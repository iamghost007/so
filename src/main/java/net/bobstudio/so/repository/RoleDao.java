package net.bobstudio.so.repository;

import net.bobstudio.so.domain.Role;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 基于Spring Data JPA的Dao接口, 自动根据接口生成实现.
 * 
 * CrudRepository默认有针对实体对象的CRUD方法.
 * 
 * Spring Data JPA 还会解释新增方法名生成新方法的实现.
 */
public interface RoleDao extends PagingAndSortingRepository<Role, Long>, JpaSpecificationExecutor<Role> {

	//Role findByEmail(String email);
	
	@Modifying
	@Query("delete from Role  where id=?1")
	void deleteRole(Long id);
	
	@Query("from Role where id>0")
	Iterable<Role> findRoles();

}
