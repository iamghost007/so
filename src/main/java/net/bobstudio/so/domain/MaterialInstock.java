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

/**
 * @author Bob Zhang
 * 2016.9.28
 */
@Entity
@Table(name = "t_mate_instock_info")
public class MaterialInstock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@ManyToOne
	@JoinColumn(name = "mate_id")
	public Material material;
	
	@Column(name="gb_standard")
	public String standard;
	
	@Column(name="mate_barcode")
	public String barcode;
	
	@Column(name="mis_count")
	public Integer numStock;
	
	public Date misDate;		//'入库时间'
	
	public Date purchaseDatetime;	//'进货日期'
	
	@ManyToOne
	@JoinColumn(name="purchase_em_id")
	public Account purchase;	//进货人
	
	@ManyToOne
	@JoinColumn(name="mis_em_id")
	public Account instocker;	//入库人
	
	@Column(name="mis_receipt")
	public String receipt;
	
	@Column(name="mate_price")
	public Double cost;
	
	@ManyToOne
	@JoinColumn(name="mate_supplier_id") 
	public Supplier supplier;
	
	public String remark;

	public MaterialInstock(){
		//
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
