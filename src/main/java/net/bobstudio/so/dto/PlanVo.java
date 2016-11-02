package net.bobstudio.so.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PlanVo {
	private Long id;

	private String productName;

	private String productType;

	private Double productAmount;

	private String customer;

	public PlanVo() {
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public Double getProductAmount() {
		return productAmount;
	}


	public void setProductAmount(Double productAmount) {
		this.productAmount = productAmount;
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
