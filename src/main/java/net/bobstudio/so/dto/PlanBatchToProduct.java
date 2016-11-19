package net.bobstudio.so.dto;

import java.util.List;

public class PlanBatchToProduct {
	private String status;
	
	private String content;
	
	private List<String> planIds;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getPlanIds() {
		return planIds;
	}

	public void setPlanIds(List<String> planIds) {
		this.planIds = planIds;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
