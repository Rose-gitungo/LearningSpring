package tech.csm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @ToString
public class salesSlave {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sNo;

@ManyToOne
@JoinColumn(name="salesId")
	private salesMaster salesMaster;

	@ManyToOne
	@JoinColumn(name="itemId")
	private itemMaster itemMaster;
	
	private Integer salesQty;
}
