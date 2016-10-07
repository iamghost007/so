package net.bobstudio.so.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AccountVo {
	public Long id;
	public String email;
	public String name;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
