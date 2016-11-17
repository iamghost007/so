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
public class MaterialInstockVo {
	private Long id;
	
	private MaterialVo material;
	
	private String standard;
	
	private String barcode;		//'原料条码'
	
	private Integer numStock;	//'入库数量'
	
	private Date misDate;		//'入库时间'
	
	public Date purchaseDatetime;	//'进货日期'
	
	public AccountVo purchase;	//进货人
	
	private AccountVo instocker;	//入库人
	
	private String receipt;		//'入库单据号'
	
	private Double cost;
	
	private SupplierVo supplier;
	
	private String remark;

	public MaterialInstockVo(){
		//
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MaterialVo getMaterial() {
		return material;
	}

	public void setMaterial(MaterialVo material) {
		this.material = material;
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

	public Date getMisDate() {
		return misDate;
	}

	public void setMisDate(Date misDate) {
		this.misDate = misDate;
	}

	public Date getPurchaseDatetime() {
		return purchaseDatetime;
	}

	public void setPurchaseDatetime(Date purchaseDatetime) {
		this.purchaseDatetime = purchaseDatetime;
	}

	public AccountVo getPurchase() {
		return purchase;
	}

	public void setPurchase(AccountVo purchase) {
		this.purchase = purchase;
	}

	public AccountVo getInstocker() {
		return instocker;
	}

	public void setInstocker(AccountVo instocker) {
		this.instocker = instocker;
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

	public SupplierVo getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierVo supplier) {
		this.supplier = supplier;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
