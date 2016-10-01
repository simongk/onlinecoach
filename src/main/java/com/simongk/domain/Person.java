package com.simongk.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Person {

	@Id
	@GeneratedValue
	private long id;

	private String firstName;
	private String lastName;

	private String squatProblem;

	private BigDecimal progressionType;
	private BigDecimal squatWeight;
	private BigDecimal benchWeight;
	private BigDecimal deadliftWeight;
	private BigDecimal rowWeight;
	private BigDecimal ohpWeight;

	private String emailAddress;

}
