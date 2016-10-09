package net.bobstudio.so.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "t_employee")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	public String code;
	public String name;
	public String duty;
	public String roler;
	public String phone;
	public String email;
	public String password;
	public String family_addr;
	public String remark;

	public Account() {
		// do nothing
	}

	public Account(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
