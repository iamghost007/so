package net.bobstudio.so.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

// JPA实体类的标识
@Entity
@Table(name = "t_plan_product")
public class ProductInPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String name;

	@OneToOne
	@JoinColumn(name = "productId")
	@NotFound(action = NotFoundAction.IGNORE)
	public Product product;

	public Double productPrice;
	
	public Double productAmount;

	public Double realAmount = 0d;

	public Double productLength;

	public String productRemark;

	@ManyToOne
	@JoinColumn(name = "planId")
	@NotFound(action=NotFoundAction.IGNORE)
	public Plan plan;

	public ProductInPlan() {

	}

	public ProductInPlan(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
