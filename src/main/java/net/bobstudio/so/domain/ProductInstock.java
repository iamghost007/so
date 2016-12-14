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
@Table(name = "t_prod_instock_info")
public class ProductInstock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@ManyToOne
	@JoinColumn(name = "prod_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Product product;
	
	@Column(name="gb_standard")
	public String standard;
	
	@Column(name="prod_barcode")
	public String barcode;
	
	@Column(name="pis_count")
	public Integer numStock;
	
	@Column(name="pis_date")
	public Date pisDate;
	
	@ManyToOne
	@JoinColumn(name="pis_em_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Account instocker;	//入库人
	
	@Column(name="pis_receipt")
	public String receipt;
	
	@Column(name="prod_cost")
	public Double cost;
	
	public String remark;

	public ProductInstock(){
		//
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
