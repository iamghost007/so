package net.bobstudio.so.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

// JPA实体类的标识
@Entity
@Table(name = "t_supplier")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(name = "sp_name")
	public String name;
	  
	public String code;
	
	@Column(name = "sp_addr")
	public String address;
	
	public Date enabledDate;
	
	@Column(name = "sp_contacter")
	public String contacter;
	
	@Column(name = "sp_contacter_phone")
	public String phone;
	
	public String status;

	@OneToOne
	@JoinColumn(name="sales_person_id")
	public Account salesman;
	
	public String remark;

	public Supplier() {

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
