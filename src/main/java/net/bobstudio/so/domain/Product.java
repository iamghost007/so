/**
 * 
 */
package net.bobstudio.so.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Bob Zhang
 * 2016.9.28
 */
@Entity
@Table(name = "tb_product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(name="prod_code")
	public String code;
	
	@Column(name="prod_name")
	public String name;
	
	@Column(name="prod_gb_standard")
	public String standard;
	
	@Column(name="prod_type")
	public String type;
	
	@Column(name="prod_num_stock")
	public Integer numStock;
	
	@Column(name="prod_num_alarm")
	public Integer numAlarm;
	
//	@Column(name="prod_drawing_code")
//	public String drawingCode;
	
	public String remark;

	public Product(){
		//
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
