package com.csm.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @Entity @ToString
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	@ManyToOne
	@JoinColumn(name = "Uid")
	private User user;
	private String subtitle;
	@Column(name="image")
	private String image;
	private String description;
	private boolean vegeterian;
	private LocalDateTime createdAt;
	private List<Long> likes = new ArrayList<>();

}
