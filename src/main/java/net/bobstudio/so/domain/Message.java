package net.bobstudio.so.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

// JPA实体类的标识
@Entity
@Table(name = "t_message")
public class Message {

	// JPA 主键标识, 策略为由数据库生成主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@ManyToOne
	@JoinColumn(name = "sender")
	@NotFound(action=NotFoundAction.IGNORE)
	public Account sender;

	public String content;
	
	public String status;
	
	@ManyToOne
	@JoinColumn(name = "plan")
	@NotFound(action=NotFoundAction.IGNORE)
	public Plan plan;

	public Date receiveDate;

	public Message() {

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
