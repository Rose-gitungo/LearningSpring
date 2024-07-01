package tech.csm.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @ToString
public class salesMaster {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer salesId;
	private Date dateofSales;
	private String customerName;
	
	@OneToMany(mappedBy = "salesMaster")
    private List<salesSlave> salesSlaves;
}
