package net.bobstudio.so.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;

// JPA实体类的标识
@Entity
@Table(name = "t_plan")
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@OneToOne
	@JoinColumn(name="productId")
	public Product product;

	//public String productName;

	//public String productType;
	
	//public String productCode;
	
	public Double productAmount;
	
	public Double realAmount;
	
	public Double productLength;
	
	public String customer;
	
	public String orderType;
	
	public String remark;
	
	public String status;

	@OneToOne
	@JoinColumn(name="salesman")
	public Account salesman;
	
	@OneToOne
	@JoinColumn(name="sponsor")
	public Account sponsor;

	public Date orderDate;
	
	@OneToMany(mappedBy = "plan")
	public List<Message> messages = Lists.newArrayList();

	public Plan() {

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
