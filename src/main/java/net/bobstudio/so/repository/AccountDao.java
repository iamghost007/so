package net.bobstudio.so.repository;

import net.bobstudio.so.domain.Account;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * 基于Spring Data JPA的Dao接口, 自动根据接口生成实现.
 * 
 * CrudRepository默认有针对实体对象的CRUD方法.
 * 
 * Spring Data JPA 还会解释新增方法名生成新方法的实现.
 */
public interface AccountDao extends CrudRepository<Account, Long> {

	Account findByEmail(String email);
	
	Account findByCode(String code);
	
	Iterable<Account> findAllByStatus(String status);
	
	@Modifying
	@Query("delete from Account  where id=?1")
	void deleteAccount(Long id);

	@Query("select count(1) from Account where code=?1")
	int existsByCode(String code);
	
	@Modifying
	@Query("update Account ac set ac.password=:password where ac.id=:id")
	void updatePasswordById(@Param("password")String password, @Param("id")Long id);

}
