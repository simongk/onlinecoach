package com.simongk.services;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.simongk.domain.Person;

public class PlanGeneratorTest {

	private Person person;
	private PlanGenerator planGenerator;
	private SquatPlanStrategy squatPlanStrategy;
	
	@Before
	public void setUp() throws Exception {
		person = new Person();
		person.setFirstName("Michal");
		person.setLastName("Zazi");
		person.setSquatProblem("Good Morning");
		person.setProgressionType(new BigDecimal("2.5"));
		person.setBenchWeight(new BigDecimal(100));
		person.setDeadliftWeight(new BigDecimal(100));
		person.setSquatWeight(new BigDecimal(100));
		planGenerator = new PlanGenerator(person);
		squatPlanStrategy = new GoodMorningStrategy();

	}
	
	@Test
	public void testSetPersonBench(){
		assertEquals(person.getBenchWeight(), new BigDecimal(100));
	}
	
	
	@Test
	public void testGenerate() throws Exception {
		planGenerator.generatePlan(squatPlanStrategy);
	}
	

}
