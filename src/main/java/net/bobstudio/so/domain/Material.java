package net.bobstudio.so.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "tb_material")
public class Material {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	public String mate_code;
	public String mate_name;
	public String mate_gb_standard;
	public Integer mate_num_stock; // 库存量
	public Integer mate_num_alarm; // 告警阈值
	public String remark;

	public Material() {
		// do nothing;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
