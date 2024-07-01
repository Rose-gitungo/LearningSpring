package tech.csm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSumDTO {
	private String customerName;
	private Long totalItemsPurchased;
    private Double totalBilledAmount;
    
    
}
