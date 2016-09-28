package net.bobstudio.so.repository;

import net.bobstudio.so.domain.Account;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 基于Spring Data JPA的Dao接口, 自动根据接口生成实现.
 * 
 * CrudRepository默认有针对实体对象的CRUD方法.
 * 
 * Spring Data JPA 还会解释新增方法名生成新方法的实现.
 */
public interface AccountDao extends CrudRepository<Account, Long> {

	Account findByEmail(String em_email);
	
	@Modifying
	@Query("delete from Account  where id=?1")
	void deleteAccount(Long id);

}
