package net.bobstudio.so.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Bob Zhang
 * 2016.9.28
 */
@Entity
@Table(name = "t_prod_outstock_info")
public class ProductOutstock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@ManyToOne
	@JoinColumn(name = "prod_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	public Product product;
	
	@Column(name="gb_standard")
	public String standard;
	
	@Column(name="prod_barcode")
	public String barcode;
	
	@Column(name="pos_count")
	public Integer numStock;
	
	public Date posDate;	//出库时间
	
	public String remark;
	
	@Column(name="pos_receipt")
	public String receipt;	//出库单据号
	
	@ManyToOne
	@JoinColumn(name="pos_em_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Account outstocker;	//出库人
	
	@ManyToOne 
	@JoinColumn(name="pos_salesperson_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Account salesman;	//业务员
	
	@ManyToOne 
	@JoinColumn(name="pos_customer_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Customer customer;
	
	public ProductOutstock(){
		//
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
