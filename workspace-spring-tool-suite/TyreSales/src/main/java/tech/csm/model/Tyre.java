package tech.csm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter @Setter @ToString
public class Tyre {
	@Id
	@Column(name="tyre_id")
	private Integer tyreId;
	
	@Column(name="tyre_name")
	private String tyreName;
	
	private Integer quantity;
	
	@Column(name="unit_price")
	private Double unitPrice;
	
	
	
}
