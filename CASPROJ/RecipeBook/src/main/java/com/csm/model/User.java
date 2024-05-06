package com.csm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @Entity @ToString
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Uid;
@JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String email;
	private String fullName;
	
}
