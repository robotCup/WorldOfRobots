package spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.model.Competition;
import spring.model.User;
import spring.service.CompetitionService;

@Controller
@RequestMapping("/")
public class CompetitionController {	
	
	@Autowired private CompetitionService competitionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String Index(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		session.setAttribute("user", user);
		List<Competition> competitions = competitionService.findAll();
		model.addAttribute("competitions", competitions);
		return "home";
	}
	
	@RequestMapping(value="/competitions", method = RequestMethod.GET)
	public String Competitions(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		session.setAttribute("user", user);
		List<Competition> competitions = competitionService.findAll();
		model.addAttribute("competitions", competitions);
		return "competitions";
	}
	
	@RequestMapping(value="/competitions/myCompetitions", method = RequestMethod.GET)
	public String myCompetitions(Model model, HttpServletRequest request){		
		
		User user = (User) request.getSession().getAttribute("user");
		List<Competition> competitions = competitionService.findAllMyCompetitions(user.getId());
		model.addAttribute("competitions", competitions);
		return "myCompetitions";
	}
	
	@RequestMapping(value="/competitions/add", method = RequestMethod.GET)
	public String prepareToAdd(Model model,HttpServletRequest request){		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return UserController.prepareConnexion(model);
		}
		else{
		session.setAttribute("user", user);
		model.addAttribute("add", new AddCompetition());
		return "addCompetition";
		}	
	}
	
	@RequestMapping(value="/competitions/toAdd", method = RequestMethod.POST)
	public String toAdd(@ModelAttribute ("add") AddCompetition cardCompetition, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return UserController.prepareConnexion(model);
		}
		else{
			try{
				if(cardCompetition.getDate_start() != ""){
					this.competitionService.createCompetition(user.getId(), cardCompetition.getName(), cardCompetition.getDescription(), cardCompetition.getDate_start(),
							cardCompetition.getRobot_max(), cardCompetition.getAddress(), cardCompetition.getGeolocation(), cardCompetition.getDuration(), "", "", "", "");
				}
				else if(cardCompetition.getDate_start_1() != "" && (cardCompetition.getDate_start_2() != "" || cardCompetition.getDate_start_3() != "" || cardCompetition.getDate_start_4() != "")){
					this.competitionService.createCompetition(user.getId(), cardCompetition.getName(), cardCompetition.getDescription(), cardCompetition.getDate_start(),
							cardCompetition.getRobot_max(), cardCompetition.getAddress(), cardCompetition.getGeolocation(), cardCompetition.getDuration(), cardCompetition.getDate_start_1(), cardCompetition.getDate_start_2(), cardCompetition.getDate_start_3(), cardCompetition.getDate_start_4());
				}
				session.setAttribute("user", user);
				model.addAttribute("isAddCompetition", true);
				return this.prepareToAdd(model, request);
			}
			catch(Exception e){
				session.setAttribute("user", user);
				request.setAttribute("isAddCompetition", false);
				return this.prepareToAdd(model, request);
			}
		}					
	}

	@RequestMapping(value="/competitions/card", method = RequestMethod.GET)
	public String cardCompetition(Model model,@RequestParam(value="id") final int id){		
		Competition competitions = competitionService.findById(id);
		model.addAttribute("competition", competitions);
		return "competition";
	}

}