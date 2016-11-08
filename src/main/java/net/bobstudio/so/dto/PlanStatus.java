package net.bobstudio.so.dto;

public enum PlanStatus {

	DRIFTING("待拟定"),
	APPROVE_ORDER("待审核"), REVIEW_ORDER("待重拟"), TO_PRODUCT("待计划生产"), 
	PULL_MATERIAL("待领料"),PUSH_PRODUCT("待产品入库"),PULL_PRODUCT("待产品出库"),
	PLAN_OVER("结束");

	private String description;

	PlanStatus(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

}