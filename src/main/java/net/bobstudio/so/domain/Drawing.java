package net.bobstudio.so.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Bob Zhang[zzb205@163.com] 2016年9月29日
 */
@Entity
@Table(name = "t_drawing")
public class Drawing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(name = "drw_name")
	public String name;

	@OneToOne
	@JoinColumn(name="drw_designer_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Account designer;

	@Column(name = "drw_date")
	public Date date;

	@OneToOne
	@JoinColumn(name = "prod_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Product product;

	public String remark;
	
	public String status;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "drw_img", columnDefinition = "BLOB",nullable=true)
	public byte [] drwImg;
}
