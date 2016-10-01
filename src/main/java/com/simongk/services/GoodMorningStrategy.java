package com.simongk.services;

public class GoodMorningStrategy implements SquatPlanStrategy {
	
	private String prefatigue;
	
	@Override
	public String setPrefatigue() {
	
		prefatigue = "1.Split Squat 3x12 3010" +
					"2. Glute Bridge 2x20 3010" +
				"3.FireHydrant 2x20 3010";
		
		return prefatigue;
		
	}
	

}
