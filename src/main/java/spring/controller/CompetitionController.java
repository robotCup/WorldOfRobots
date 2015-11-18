package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.model.Competition;

import spring.service.CompetitionService;

@Controller
@RequestMapping("/")
public class CompetitionController {	
	
	@Autowired private CompetitionService competitionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String Index(Model model){
		List<Competition> competitions = competitionService.findAll();
		model.addAttribute("competitions", competitions);
		return "home";
	}
	
	@RequestMapping(value="/competitions", method = RequestMethod.GET)
	public String Competitions(Model model){		
		List<Competition> competitions = competitionService.findAll();
		model.addAttribute("competitions", competitions);
		return "competitions";
	}
	
	@RequestMapping(value="/competitions/add", method = RequestMethod.GET)
	public String add(Model model){		
		
		model.addAttribute("add", new CardCompetition());
		return "addCompetition";
	}
	

	@RequestMapping(value="/competitions/card", method = RequestMethod.GET)
	public String cardCompetition(Model model,@RequestParam(value="id") final int id){		
		Competition competitions = competitionService.findById(id);
		model.addAttribute("competition", competitions);
		return "competition";
	}

}