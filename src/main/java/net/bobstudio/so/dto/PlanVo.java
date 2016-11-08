package net.bobstudio.so.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PlanVo {
	private Long id;
	
	private Long productId;

	private String productName;

	private String productType;

	private Double productAmount;

	private String customer;
	
	private PlanStatus status;
	
	private AccountVo sponsor;
	
	private Date orderDate;
	
	private List<MessageVo> messages;

	public PlanVo() {
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public PlanStatus getStatus() {
		return status;
	}

	public void setStatus(PlanStatus status) {
		this.status = status;
	}

	public String getStatus_cn() {
		return status != null ? status.getDescription() : "";
	}

	public AccountVo getSponsor() {
		return sponsor;
	}

	public void setSponsor(AccountVo sponsor) {
		this.sponsor = sponsor;
	}

	public List<MessageVo> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageVo> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
