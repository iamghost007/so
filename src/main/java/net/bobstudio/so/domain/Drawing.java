package net.bobstudio.so.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Bob Zhang[zzb205@163.com] 2016年9月29日
 */
@Entity
@Table(name = "t_drawing")
public class Drawing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(name = "drw_code")
	public String code;

	@Column(name = "drw_name")
	public String name;

	@Column(name = "drw_designer")
	public String designer;

	@Column(name = "drw_date")
	public Date date;

	@ManyToOne
	@JoinColumn(name = "drw_prod_id")
	public Product product;

	public String remark;
}
