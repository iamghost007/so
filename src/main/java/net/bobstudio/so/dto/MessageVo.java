package net.bobstudio.so.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MessageVo {
	private Long id;
	
	private AccountVo sender;

	private PlanStatus content;
	
	private PlanStatus status;
	
	//private PlanVo plan;
	
	private Date receiveDate;

	public MessageVo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccountVo getSender() {
		return sender;
	}

	public void setSender(AccountVo sender) {
		this.sender = sender;
	}

	public PlanStatus getContent() {
		return content;
	}
	
	public String getNext(){
		return content != null ? content.getDescription() : "未知";
	}

	public void setContent(PlanStatus content) {
		this.content = content;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public PlanStatus getStatus() {
		return status;
	}

	public void setStatus(PlanStatus status) {
		this.status = status;
	}
	
	public String getWorkflow(){
		//YYY 审核， 时间：2016-11-11，下一步：待计划生产
		StringBuilder sb = new StringBuilder();
		sb.append(sender.getName())
		  .append(" ")
		  .append(content.getDescription().substring(1))
		  .append("， 时间：")
		  .append( new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(receiveDate))
		  .append("。 下一步：")
		  .append(status.getDescription());
		
		return sb.toString();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
