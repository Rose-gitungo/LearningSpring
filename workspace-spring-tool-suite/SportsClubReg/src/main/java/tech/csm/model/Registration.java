package tech.csm.model;

import java.io.Reader;
import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Registration implements Serializable {

	private Integer registrationid;
	
	private String applicantname;
	
	private String email;
	
	private String mobileno;
	
	private String gender;
	
	//private Date dob;
	
	private  String imagepath;
	
	private ClubMaster clubMaster;
	
	private SportsMaster sportsMaster;
	
}
