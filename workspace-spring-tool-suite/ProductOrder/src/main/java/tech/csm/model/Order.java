package tech.csm.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Order {

	private Integer orderId;
	
	private Date orderDate;
	
	private Product product;
	
	private String orderQty;
}
