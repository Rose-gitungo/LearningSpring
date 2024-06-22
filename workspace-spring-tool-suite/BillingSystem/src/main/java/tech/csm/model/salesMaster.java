package tech.csm.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity @Data
public class salesMaster {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer salesId;
	private Date dateofSales;
	private String customerName;
}
