/**
 * 
 */
package net.bobstudio.so.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Bob Zhang
 * 2016.9.28
 */
public class ProductInstockVo {
	private Long id;
	
	private ProductVo product;
	
	private String standard;
	
	private String barcode;		//'产品条码'
	
	private Integer numStock;	//'入库数量'
	
	private Date pisDate;		//'入库时间'
	
	private String remark;
	
	private String receipt;		//'入库单据号'
	
	private Double cost;
	
	private AccountVo instocker;	//入库人

	public ProductInstockVo(){
		//
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductVo getProduct() {
		return product;
	}

	public void setProduct(ProductVo product) {
		this.product = product;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getNumStock() {
		return numStock;
	}

	public void setNumStock(Integer numStock) {
		this.numStock = numStock;
	}

	public Date getPisDate() {
		return pisDate;
	}

	public void setPisDate(Date pisDate) {
		this.pisDate = pisDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public AccountVo getInstocker() {
		return instocker;
	}

	public void setInstocker(AccountVo instocker) {
		this.instocker = instocker;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
