package net.bobstudio.so.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "t_material")
public class Material {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	public String code;
	
	@Column(name = "mate_name")
	public String name;
	
	@Column(name = "mate_gb_standard")
	public String standard;
	
	public String gbCode;
	
	@Column(name = "mate_num_stock")
	public Integer numStock; // 库存量

	@Column(name = "mate_num_alarm")
	public Integer numAlarm; // 告警阈值
	
	public String remark;
	
	public String status;

	public Material() {
		// do nothing;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
