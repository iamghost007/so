package net.bobstudio.so.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.bobstudio.so.dto.PlanStatus;

import org.apache.commons.lang3.builder.ToStringBuilder;

// JPA实体类的标识
@Entity
@Table(name = "t_plan")
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public Long productId;

	public String productName;

	public String productType;
	
	public Double productAmount;
	
	public String customer;
	
	public String status;
	
	@OneToOne
	@JoinColumn(name="sponsor")
	public Account sponsor;

	public Date orderDate;

	public Plan() {

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
