package tech.csm.dto;

public class SalesSummaryDTO {
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getSalesQty() {
		return salesQty;
	}
	public void setSalesQty(Integer salesQty) {
		this.salesQty = salesQty;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	private String itemName;
	    public SalesSummaryDTO(String itemName, Integer salesQty, Double unitPrice, Double totalAmount) {
		super();
		this.itemName = itemName;
		this.salesQty = salesQty;
		this.unitPrice = unitPrice;
		this.totalAmount = totalAmount;
	}
		private Integer salesQty;
	    private Double unitPrice;
	    private Double totalAmount;
}
