package net.bobstudio.so.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.google.common.collect.Lists;

// JPA实体类的标识
@Entity
@Table(name = "t_plan")
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String name;
	
	@OneToOne
	@JoinColumn(name="customer")
	@NotFound(action=NotFoundAction.IGNORE)
	public Customer customer;
	
	public String orderType;
	
	public String remark;
	
	public String status;

	@OneToOne
	@JoinColumn(name="salesman")
	@NotFound(action=NotFoundAction.IGNORE)
	public Account salesman;
	
	@OneToOne
	@JoinColumn(name="sponsor")
	@NotFound(action=NotFoundAction.IGNORE)
	public Account sponsor;

	public Date orderDate;
	
	public String productCodes;
	
	@OneToMany(mappedBy = "plan")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<Message> messages = Lists.newArrayList();
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },mappedBy = "plan")
	@NotFound(action=NotFoundAction.IGNORE)
	public List<ProductInPlan> planProducts = Lists.newArrayList();

	public Plan() {

	}

	public Plan(Long id, String status) {
		this.id = id;
		this.status = status;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
