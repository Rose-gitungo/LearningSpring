package tech.csm.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SportsMaster {

	private Integer sportsid;
	
	private String sportsname;
	
	private ClubMaster clubMaster;
	
	private Double fees;
}
