package com.simongk.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class ProgressionCalculationTest {

	private BigDecimal smallProgression;
	private BigDecimal bigProgression;
	private BigDecimal startingWeight;
	private ProgressionCalculation progressstrategy;
	private List<BigDecimal> liftWeeks;
	
	@Before
	public void setUp() throws Exception {
		progressstrategy = new ProgressionCalculation();
		smallProgression=new BigDecimal(2.5);
		bigProgression=new BigDecimal(5);
		startingWeight=new BigDecimal(100);
		liftWeeks = progressstrategy.getLiftWeeks();
		

	}

	@Test
	public void testSmallProgression() {
		
		progressstrategy.calculateProgression(smallProgression, startingWeight);
		
		assertEquals(new BigDecimal(102.5),liftWeeks.get(1));
		
		assertEquals(new BigDecimal("105.0"),liftWeeks.get(2));
	
	
	}

	@Test
	public void testBigProgression() {
		
		progressstrategy.calculateProgression(bigProgression, startingWeight);
		
		assertEquals(new BigDecimal(105), liftWeeks.get(1));
		
		assertEquals(new BigDecimal(110),liftWeeks.get(2));
		
	}
}
