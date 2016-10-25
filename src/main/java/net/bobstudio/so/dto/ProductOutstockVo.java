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
public class ProductOutstockVo {
	private Long id;

	//private String code;
	
	private ProductVo product;
	
	private String standard;
	
	private String barcode;
	
	private Integer numStock;
	
	private Date posDate;
	
	private String remark;
	
	private String receipt;	//出库单据号
	
	private String outstocker;	//出库人
	
	private String salesman;	//业务员

	public ProductOutstockVo(){
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

	public Date getPosDate() {
		return posDate;
	}

	public void setPosDate(Date posDate) {
		this.posDate = posDate;
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

	public String getOutstocker() {
		return outstocker;
	}

	public void setOutstocker(String outstocker) {
		this.outstocker = outstocker;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
