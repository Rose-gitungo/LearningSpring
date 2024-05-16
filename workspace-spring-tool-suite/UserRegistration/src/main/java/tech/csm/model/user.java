package tech.csm.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Setter @Getter @ToString
public class user {

	@Id
	@NotBlank(message =  "User Id cannot be blank")
	@Column(name="user_id" , unique = true)
	private String userId;
	
	@NotBlank(message =  "Password cannot be blank")
	private String password;
	
	
	@NotBlank(message =  "Upload a file")
	@Column(name="file_name")
	private String fileName;
	
	@Email
	@NotBlank(message =  "Email cannot be blank")
	private String email;
	
	@NotBlank(message =  "Mobile No cannot be blank")
	@Column(name="mobile_no")
	private String mobileNo;
	
	@NotBlank(message =  "Name cannot be blank")
	@Column(name="full_name")
	private String fullName;
	
	private String address;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	@NotBlank(message =  "Gender cannot be blank")
	private String gender;
}
