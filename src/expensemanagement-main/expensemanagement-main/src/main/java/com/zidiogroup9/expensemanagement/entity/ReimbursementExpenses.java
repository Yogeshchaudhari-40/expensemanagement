package com.zidiogroup9.expensemanagement.entity;

import java.util.Date;

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
public class ReimbursementExpenses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reimbursementId;
	@Column(nullable = false, unique = false)
	private int employeeId;
	@Column(nullable = false, unique = false)
	private double totalAmount;
	@Enumerated
	private PaymentMethod paymentMethod;
	@Column(nullable = false, unique = false)
	private Date paymentDate;
	@Enumerated
	private Decision decision;
	@CurrentTimestamp
	private CurrentTimestamp createdAt;
	@Column(nullable = false, unique = false)
	private String description;
}
