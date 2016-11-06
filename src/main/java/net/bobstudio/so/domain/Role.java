package net.bobstudio.so.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.ImmutableList;

@Entity
@Table(name = "t_roler")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public Long pId;

	@Column(name="rl_name")
	public String name;
	
	@Column(name="rl_priv")
	public String priv;
	
	public String remark;

	public Role() {
		// do nothing
	}

	public Role(Long id) {
		this.id = id;
	}
	
	@Transient
	public List<String> getPermissionList() {
		return ImmutableList.copyOf(StringUtils.split(priv, ","));
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
