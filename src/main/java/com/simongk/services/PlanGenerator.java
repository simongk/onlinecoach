package com.simongk.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.simongk.domain.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlanGenerator {

	private Person person;
	private List<BigDecimal> squatProgression;

	@Autowired
	public PlanGenerator(Person person) {
		this.person = person;
	}

	
	public void generatePlan(SquatPlanStrategy squatPlanStrategy) {
		try {

			File file = new File("plan.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
		
			FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			squatProgression = new ArrayList<>();
			squatProgression.add(person.getSquatWeight());
			for (int i = 1; i < 9; i++) {
				squatProgression.add(i, squatProgression.get(i - 1).add(person.getProgressionType()));
			}
			
			String weekTitle = "Week 1";
			String weekDay1 = "Monday";
			String weekDay2 = "Wednesday";
			String weekDay3 = "Friday";

			String squat = "Squat 3x5";
			String sqWeight1 =	squatProgression.get(0).toString();

			String benchPress = "Bench Press 3x5";
			String bpWeight1 = person.getBenchWeight().toString();

			String deadlift = "Deadlift 1x5";
			String dlWeight1 = person.getBenchWeight().toString();
			
		
	

			bufferedWriter.write(person.getFirstName());
			bufferedWriter.newLine();
			bufferedWriter.write(person.getLastName());
			bufferedWriter.newLine();
			bufferedWriter.write(person.getSquatProblem());
			bufferedWriter.newLine();
			bufferedWriter.write(squatPlanStrategy.setPrefatigue());

			String[] weeekOne = { weekTitle, weekDay1, squat, sqWeight1, benchPress, bpWeight1, deadlift, dlWeight1,
					weekDay2, squat, squatProgression.get(1).toString(), };

			for (String word : weeekOne) {
				bufferedWriter.write(word);
				bufferedWriter.newLine();
			}

			bufferedWriter.close();

			log.info("File created");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
