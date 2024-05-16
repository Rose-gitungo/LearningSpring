package tech.csm.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Product {

	private Integer productId;
	
	private String prodName;
	
	private Double prodRate;
	
	private String prodQty; 
}
