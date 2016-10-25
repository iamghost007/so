/**
 * 
 */
package net.bobstudio.so.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Bob Zhang
 * 2016.9.28
 */
public class ProductVo {
	public Long id;

	//public String code;
	
	public String name;
	
	public String standard;
	
	public String type;
	
	public Integer numStock;
	
	public Integer numAlarm;
	
	public String remark;
	
	public String status;

	public ProductVo(){
		//
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNumStock() {
		return numStock;
	}

	public void setNumStock(Integer numStock) {
		this.numStock = numStock;
	}

	public Integer getNumAlarm() {
		return numAlarm;
	}

	public void setNumAlarm(Integer numAlarm) {
		this.numAlarm = numAlarm;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
