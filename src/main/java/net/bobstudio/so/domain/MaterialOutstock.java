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
@Table(name = "t_mate_outstock_info")
public class MaterialOutstock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@ManyToOne
	@JoinColumn(name = "mate_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Material material;
	
	@Column(name="gb_standard")
	public String standard;
	
	@Column(name="mate_barcode")
	public String barcode;
	
	@Column(name="mos_count")
	public Integer numStock;		//出库数量
	
	public Date mosDate;		//'出库时间'
	
	@ManyToOne
	@JoinColumn(name="consumer_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Account consumer;	//'领用人'
	
	@ManyToOne
	@JoinColumn(name="mos_em_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Account outstocker;	//出库人
	
	@Column(name="mos_receipt")
	public String receipt;
	
	public String remark;

	public MaterialOutstock(){
		//
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
