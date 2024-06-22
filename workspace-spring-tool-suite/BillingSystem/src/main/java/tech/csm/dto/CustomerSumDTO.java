package tech.csm.dto;


public class CustomerSumDTO {

	private String customerName;
	private Integer totalItemsPurchased;
    private Double totalBilledAmount;
    
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getTotalItemsPurchased() {
		return totalItemsPurchased;
	}
	public void setTotalItemsPurchased(Integer totalItemsPurchased) {
		this.totalItemsPurchased = totalItemsPurchased;
	}
	public Double getTotalBilledAmount() {
		return totalBilledAmount;
	}
	public void setTotalBilledAmount(Double totalBilledAmount) {
		this.totalBilledAmount = totalBilledAmount;
	}
	public CustomerSumDTO(String customerName, Integer totalItemsPurchased, Double totalBilledAmount) {
		super();
		this.customerName = customerName;
		this.totalItemsPurchased = totalItemsPurchased;
		this.totalBilledAmount = totalBilledAmount;
	}
}
