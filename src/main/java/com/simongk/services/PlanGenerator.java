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
	private List<BigDecimal> benchProgression;
	private List<BigDecimal> deadliftProgression;
	private List<BigDecimal> rowProgression;
	private List<BigDecimal> ohpProgression;
	
	private final static String weekDay1 = "Monday";
	private final static String weekDay2 = "Wednesday";
	private final static String weekDay3 = "Friday";
	private final static String squat = "Squat 3x5";
	private final static String benchPress = "Bench Press 3x5";
	private final static String deadlift = "Deadlift 1x5";
	private final static String ohp = "Military Press 3x5";
	private final static String row = "Row 3x5";
	private final static String pullup = "Pull ups 3xmax";

	@Autowired
	public PlanGenerator(Person person) {
		this.person = person;
	}

	
	public void generatePlan(SquatPlanStrategy squatPlanStrategy) {
		
		try {
			File file = createFile();
			calculateProgressions();			
			writePlanToFile(squatPlanStrategy, file);
			log.info("File created");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	private void writePlanToFile(SquatPlanStrategy squatPlanStrategy, File file) throws IOException {
		FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		bufferedWriter.write(person.getFirstName());
		bufferedWriter.newLine();
		bufferedWriter.write(person.getLastName());
		bufferedWriter.newLine();
		bufferedWriter.write(person.getSquatProblem());
		bufferedWriter.newLine();
		bufferedWriter.write(squatPlanStrategy.setPrefatigue());
		bufferedWriter.newLine();

		String[] plan = { 
				"Week 1", 
				weekDay1, 
				squat, squatProgression.get(0).toString(), 
				benchPress, benchProgression.get(0).toString(), 
				deadlift, deadliftProgression.get(0).toString(),
				
				weekDay2, 
				squat, squatProgression.get(1).toString(),
				ohp, ohpProgression.get(0).toString(),
				row, rowProgression.get(0).toString(),
				pullup,
				
				weekDay3,
				squat, squatProgression.get(2).toString(), 
				benchPress, benchProgression.get(1).toString(), 
				deadlift, deadliftProgression.get(1).toString(),
				
				"Week 2",
				weekDay1,
				squat, squatProgression.get(3).toString(),
				ohp, ohpProgression.get(1).toString(),
				row, rowProgression.get(1).toString(),
				pullup,
				
				weekDay2,
				squat, squatProgression.get(4).toString(), 
				benchPress, benchProgression.get(2).toString(), 
				deadlift, deadliftProgression.get(2).toString(),

				weekDay3,
				squat, squatProgression.get(5).toString(),
				ohp, ohpProgression.get(2).toString(),
				row, rowProgression.get(2).toString(),
				pullup,
				
				"Week 3",					
				weekDay1, 
				squat, squatProgression.get(6).toString(), 
				benchPress, benchProgression.get(3).toString(), 
				deadlift, deadliftProgression.get(3).toString(),
						
				weekDay2, 
				squat, squatProgression.get(7).toString(),
				ohp, ohpProgression.get(3).toString(),
				row, rowProgression.get(3).toString(),
				pullup,

				weekDay3,
				squat, squatProgression.get(8).toString(), 
				benchPress, benchProgression.get(4).toString(), 
				deadlift, deadliftProgression.get(4).toString(),
			
		};

		for (String word : plan) {
			bufferedWriter.write(word);
			bufferedWriter.newLine();
		}

		bufferedWriter.close();
	}


	private File createFile() throws IOException {
		File file = new File("plan.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}


	private void calculateProgressions() {

		squatProgression = new ArrayList<>();
		benchProgression = new ArrayList<>();
		deadliftProgression = new ArrayList<>();
		ohpProgression = new ArrayList<>();
		rowProgression = new ArrayList<>();
		
		benchProgression.add(person.getBenchWeight());
		deadliftProgression.add(person.getDeadliftWeight());
		ohpProgression.add(person.getOhpWeight());
		rowProgression.add(person.getRowWeight());
		squatProgression.add(person.getSquatWeight());
		
		for (int i = 1; i < 9; i++) {
			squatProgression.add(i, squatProgression.get(i - 1).add(person.getProgressionType()));
			benchProgression.add(i, benchProgression.get(i - 1).add(person.getProgressionType()));
			deadliftProgression.add(i, deadliftProgression.get(i - 1).add(person.getProgressionType()));
			rowProgression.add(i, rowProgression.get(i - 1).add(person.getProgressionType()));
			ohpProgression.add(i, ohpProgression.get(i - 1).add(person.getProgressionType()));
		}
	}

}
