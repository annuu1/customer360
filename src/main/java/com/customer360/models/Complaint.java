package com.customer360.models;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Complaint {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cid;
	private String name;
	private String moNo;
	private String subject;
	private String complaint;

	@ManyToOne
	private Customer customer;

	private LocalDate date;
	private String status;
	private String priority;
	private String assign;
	private String resolution;
	private LocalDate closureDate;
		
}
