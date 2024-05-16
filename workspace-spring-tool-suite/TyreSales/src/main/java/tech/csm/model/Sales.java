package tech.csm.model;

import java.sql.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter @Setter @ToString
public class Sales {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer salesId;
	
	private Date salesDate;
	
	@ManyToOne
	@JoinColumn(name="garage_id")
	private Garage garage;
	
	@ManyToOne
	@JoinColumn(name="tyre_id")
	private Tyre tyre;
	
	private Integer salesQuantity;
	
	
}
