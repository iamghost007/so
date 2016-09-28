/**
 * 
 */
package net.bobstudio.so.domain;

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

	public String prod_code;
	public String prod_name;
	public String prod_gb_standard;
	public String prod_type;
	public Integer prod_num_stock;
	public Integer prod_num_alarm;
	public String prod_drawing_code;
	public String remark;

	public Product(){
		//
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
