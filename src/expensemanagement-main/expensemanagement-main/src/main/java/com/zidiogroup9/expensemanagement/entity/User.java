package com.zidiogroup9.expensemanagement.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class User {

	@Id
	private int id;
	@Column(nullable = false,unique = false)
	private String name;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false,unique = false)
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Department department;
	
}
