package com.simongk.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.simongk.domain.Person;

public class SurveyControllersTest {

	private SurveyController controller;
	private Person person;
	private Model model;
	
	@Before
	public void setUp() throws Exception {
		controller = new SurveyController();
		person = new Person();
		model = new ExtendedModelMap();
		controller.survey(model);
	}

	@Test
	public void testSurveyGenerate() {
		controller.surveySubmit(person);
		assertNotNull(person);
	}

	
	
	
}
