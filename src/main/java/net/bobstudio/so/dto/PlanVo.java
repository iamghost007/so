package net.bobstudio.so.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PlanVo {
	private Long id;
	
	private ProductVo product;

	private Double productAmount;

	private Double realAmount = 0d;;
	
	private Double productLength;

	private String customer;
	
	private OrderType orderType;
	
	private String remark;
	
	private PlanStatus status;
	
	private AccountVo sponsor;
	
	private AccountVo salesman;
	
	private Date orderDate;
	
	private String content = "DRIFTING";
	
	private List<MessageVo> messages;

	public PlanVo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductVo getProduct() {
		return product;
	}

	public void setProduct(ProductVo product) {
		this.product = product;
	}

	public Double getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(Double productAmount) {
		this.productAmount = productAmount;
	}

	public Double getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}

	public Double getProductLength() {
		return productLength;
	}

	public void setProductLength(Double productLength) {
		this.productLength = productLength;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PlanStatus getStatus() {
		return status;
	}

	public void setStatus(PlanStatus status) {
		this.status = status;
	}

	public AccountVo getSponsor() {
		return sponsor;
	}

	public void setSponsor(AccountVo sponsor) {
		this.sponsor = sponsor;
	}

	public AccountVo getSalesman() {
		return salesman;
	}

	public void setSalesman(AccountVo salesman) {
		this.salesman = salesman;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<MessageVo> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageVo> messages) {
		this.messages = messages;
	}
	
	public String getStatus_cn() {
		return status != null ? status.getDescription() : "";
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
