package net.bobstudio.so.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PlanVo {
	private Long id;
	
	private String name;
	
	private CustomerVo customer;
	
	private OrderType orderType;
	
	private String remark;
	
	private PlanStatus status;
	
	private AccountVo sponsor;
	
	private AccountVo salesman;
	
	private Date orderDate;
	
	private String content = "DRIFTING";
	
	private List<ProductInPlanVo> planProducts;
	
	private List<MessageVo> messages;

	public PlanVo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerVo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerVo customer) {
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

	public List<ProductInPlanVo> getPlanProducts() {
		return planProducts;
	}

	public void setPlanProducts(List<ProductInPlanVo> planProducts) {
		this.planProducts = planProducts;
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
	
	public String getOrderType_cn(){
		return orderType != null ? orderType.getDescription() : "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
