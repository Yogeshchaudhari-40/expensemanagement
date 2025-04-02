package com.zidiogroup9.expensemanagement.entity;

import org.hibernate.annotations.CurrentTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class ExpenseApprovals {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int approvalId;
	@Column(nullable = false,unique = true)
	private long expencesId;
	@Column(nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long aproverId;
	@Enumerated
	private Decision decision;
	@Column(nullable = false, unique = true)
	private String comments;
	@CurrentTimestamp
	private CurrentTimestamp timestamp;

}
