package com.zidiogroup9.expensemanagement.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int departmentId;
	@Column(nullable = false, unique = true)
	private String departmentName;
	@Column(nullable = false, unique = false)
	private double budget;
	@Column(unique = false, nullable = false)
	private Date createdAt;

}
