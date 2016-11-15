package net.bobstudio.so.dto;

public enum OrderType {

	SALE("销售"),
	PRODUCTION("生产");

	private String description;

	OrderType(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

}