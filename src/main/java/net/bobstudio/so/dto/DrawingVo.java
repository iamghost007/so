package net.bobstudio.so.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Bob Zhang[zzb205@163.com] 2016年9月29日
 */
public class DrawingVo {
	private Long id;

	private String name;

	private AccountVo designer;

	private Date date;

	private String remark;

	private ProductVo product;

	private String status;

	private byte [] drwImg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountVo getDesigner() {
		return designer;
	}

	public void setDesigner(AccountVo designer) {
		this.designer = designer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public ProductVo getProduct() {
		return product;
	}

	public void setProduct(ProductVo product) {
		this.product = product;
	}

	public byte[] getDrwImg() {
		return drwImg;
	}

	public void setDrwImg(byte [] drwImg) {
		this.drwImg = drwImg;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
