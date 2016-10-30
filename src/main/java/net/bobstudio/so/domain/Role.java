package net.bobstudio.so.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
