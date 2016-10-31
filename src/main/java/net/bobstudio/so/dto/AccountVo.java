package net.bobstudio.so.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AccountVo {
	private Long id;

	private String code;
	private String name;
	private String duty;
	private String phone;
	private String email;
	private String password;
	private String family_addr;
	private String remark;
	private String status;
	private Boolean update = false;	//true则不更新密码/角色

	private List<RoleVo> roleList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFamily_addr() {
		return family_addr;
	}

	public void setFamily_addr(String family_addr) {
		this.family_addr = family_addr;
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

	public List<RoleVo> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleVo> roleList) {
		this.roleList = roleList;
	}

	public Boolean getUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
