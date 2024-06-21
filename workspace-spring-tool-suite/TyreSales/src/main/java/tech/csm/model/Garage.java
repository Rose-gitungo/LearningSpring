package tech.csm.model;

import org.hibernate.annotations.Collate;

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
public class Garage {
	@Id
	@Column(name="garage_id")
	private Integer garageId;
	
	@Column(name="garage_name")
	private String garageName;
	
	private String address;
	
}
