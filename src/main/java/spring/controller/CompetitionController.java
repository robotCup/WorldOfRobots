package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.Competition;
import spring.model.CompetitionDAO;

@Controller
@RequestMapping("/")
public class CompetitionController {	
	
	@Autowired private CompetitionDAO competitionDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String Index(){
		return "competitions";
	}
	
	@RequestMapping(value="/competitions", method = RequestMethod.GET)
	public String Competitions(Model model){
		
		List<Competition> competitions = competitionDAO.findAll();
		model.addAttribute("competitions", competitions);
		return "competitions";
	}	
	
}