package tech.csm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.csm.model.itemMaster;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesSummaryDTO {
	    private String itemName;
		private Integer salesQty;
	    private Double unitPrice;
	    private Double totalAmount;
}
