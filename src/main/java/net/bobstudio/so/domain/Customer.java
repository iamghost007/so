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
@Table(name = "t_customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(name = "cust_name")
	public String name;
	
	@Column(name = "cust_addr")
	public String address;
	
	public Date enableDate;
	
	@Column(name = "cust_contacter")
	public String contacter;
	
	@Column(name = "cust_contacter_phone")
	public String phone;
	
	public String status;

	@OneToOne
	@JoinColumn(name="sales_person_id")
	public Account salesman;
	
	public String remark;

	public Customer() {

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
