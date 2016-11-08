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

import net.bobstudio.so.dto.PlanStatus;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;

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
	
	@OneToMany(mappedBy = "plan")
	public List<Message> messages = Lists.newArrayList();

	public Plan() {

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
