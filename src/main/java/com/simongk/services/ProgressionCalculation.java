package com.simongk.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ProgressionCalculation {

	private List<BigDecimal> liftWeeks = new ArrayList<>();

	public List<BigDecimal> calculateProgression(BigDecimal progressionType, BigDecimal startingWeight) {

		liftWeeks.add(0, startingWeight);

		for (int i = 1; i < 9; i++) {
			liftWeeks.add(i, liftWeeks.get(i - 1).add(progressionType));
		}
		return liftWeeks;
	}

}
