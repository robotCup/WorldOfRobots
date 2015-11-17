package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.Competition;
import spring.model.CompetitionDAO;
import spring.service.CompetitionService;

@Controller
@RequestMapping("/")
public class CompetitionController {	
	
	@Autowired private CompetitionService competitionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String Index(Model model){
		//List<Competition> competitions = competitionService.findAll();
		List<String> competitions = competitionService.home();
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
		return "addCompetition";
	}
}