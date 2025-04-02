package com.zidiogroup9.expensemanagement.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table
@Entity
public class Expences {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long expencesId;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<User> users;
	@Column(nullable = false, unique = false)
	private double amount;
	@Column(nullable = false, unique = false)
	private long categoryId;
	@Column(nullable = false, unique = false)
	private Date dateOfExpence;
	@Column(unique = false, nullable = false)
	private String description;
	@Enumerated
	private Decision status;
}
