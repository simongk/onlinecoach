package com.simongk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simongk.domain.Person;
import com.simongk.services.ButtwinkStrategy;
import com.simongk.services.GoodMorningStrategy;
import com.simongk.services.MailService;
import com.simongk.services.NoProblemStrategy;
import com.simongk.services.PlanGenerator;
import com.simongk.services.SquatPlanStrategy;

@Controller
@RequestMapping("survey")
public class SurveyController {
	
	
	private PlanGenerator planGenerator;
	private SquatPlanStrategy squatPlanStrategy;
	
	@Autowired
	private MailService mail;
	
	@GetMapping
	public ModelAndView survey(Model model){
		model.addAttribute("person",new Person());
		return new ModelAndView("survey");
	}
	
	@PostMapping("/generate")
	public ModelAndView surveySubmit(@ModelAttribute("person") Person person){
		planGenerator = new PlanGenerator(person);
		squatStrategy(person);
		planGenerator.generatePlan(squatPlanStrategy);
		mail.sendWithAttachment(person);
		return new ModelAndView("thanks");
	}

	private void squatStrategy(Person person) {
		if(haveGoodMorningProblem(person)) 
			squatPlanStrategy = new GoodMorningStrategy();
		else if(haveButtwinkProblem(person))
			squatPlanStrategy = new ButtwinkStrategy();
		else
			squatPlanStrategy = new NoProblemStrategy();
	}

	private boolean haveButtwinkProblem(Person person) {
		return person.getSquatProblem().equals("Buttwink");
	}

	private boolean haveGoodMorningProblem(Person person) {
		return person.getSquatProblem().equals("Good Morning");
	}
	
	
}
