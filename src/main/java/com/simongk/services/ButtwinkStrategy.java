package com.simongk.services;

public class ButtwinkStrategy implements SquatPlanStrategy {

	private String prefatigue;
	
	@Override
	public String setPrefatigue() {
		
		prefatigue = "1.Reverse Calf Raises 3x12 3010"
				+ "2.Calf Raise 3x12 3010" 
				+ "3.Front foot elevated Split squat 3x12 3010"
				+"4.Ankle stretch 20s";
	
		return prefatigue;
	}


}
