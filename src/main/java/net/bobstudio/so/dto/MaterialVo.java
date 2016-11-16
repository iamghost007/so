package net.bobstudio.so.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MaterialVo {
	private Long id;
	
	private String code;

	private String name;
	
	private String standard;

	private String gbCode;
	
	private Integer numStock; // 库存量

	private Integer numAlarm; // 告警阈值
	
	private String remark;

	private String status;
	
	public MaterialVo() {
		// do nothing;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGbCode() {
		return gbCode;
	}

	public void setGbCode(String gbCode) {
		this.gbCode = gbCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
