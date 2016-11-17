package net.bobstudio.so.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Bob Zhang
 * 2016.9.28
 */
public class MaterialOutstockVo {
	private Long id;

	private MaterialVo material;
	
	private String standard;
	
	private String barcode;
	
	private Integer numStock;		//出库数量
	
	private Date mosDate;		//'出库时间'
	
	private AccountVo consumer;	//'领用人'
	
	private AccountVo outstocker;	//出库人
	
	private String receipt;
	
	private String remark;

	public MaterialOutstockVo(){
		//
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getMosDate() {
		return mosDate;
	}

	public void setMosDate(Date mosDate) {
		this.mosDate = mosDate;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public MaterialVo getMaterial() {
		return material;
	}

	public void setMaterial(MaterialVo material) {
		this.material = material;
	}

	public AccountVo getOutstocker() {
		return outstocker;
	}

	public void setOutstocker(AccountVo outstocker) {
		this.outstocker = outstocker;
	}

	public AccountVo getConsumer() {
		return consumer;
	}

	public void setConsumer(AccountVo consumer) {
		this.consumer = consumer;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
