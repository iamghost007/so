package net.bobstudio.so.repository;

import net.bobstudio.so.domain.Message;
import net.bobstudio.so.domain.Plan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 基于Spring Data JPA的Dao接口, 自动根据接口生成实现.
 * 
 * CrudRepository默认有针对实体对象的CRUD方法.
 */
public interface MessageDao extends CrudRepository<Message, Long> {

//	@Query("select from Message where link=?")
//	Iterable<Message> findAllByLink(Plan link);
}
