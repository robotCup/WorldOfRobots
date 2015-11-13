package spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.model.Competition;
import spring.model.CompetitionDAO;
import spring.model.User;
import spring.model.UserDAO;



@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired private UserDAO utilisateurService;
	@Autowired private CompetitionDAO competitionDAO;

	@RequestMapping(value="toConnect", method=RequestMethod.GET)
	public String prepareConnexion(Model model) {
		model.addAttribute("connexion", new Connexion());
		return "connexion";
	}
	
	
	
	@RequestMapping(value="toConnect", method = RequestMethod.POST)
	public String toConnect(@ModelAttribute ("connexion") Connexion connexion, ModelMap model,HttpServletRequest request) {//page apr�s la connexion
		User user = this.utilisateurService.findByLogin(connexion.getLogin(), connexion.getPwd());
		if (user==null || (!(user.getLogin().equals(connexion.getLogin()))&& !(user.getPwd().equals(connexion.getPwd())))){
			return "connexion";
		}
		else{
			HttpSession session =request.getSession();
			session.setAttribute("login", connexion.getLogin());
			session.setAttribute("user", user);
			List<Competition> competitions = competitionDAO.findAll();
			model.addAttribute("competitions", competitions);
			return "competitions";
		}
	}

	@RequestMapping(value="mySpace", method=RequestMethod.GET)
	public String monEspace(Model model,HttpServletRequest request) {
		HttpSession session =request.getSession();
		if(session.getAttribute("user")!=null){
			return "mySpace";
		}
		else {
			return this.prepareConnexion(model);
		}
	}
	
	@RequestMapping(value="disconnect", method=RequestMethod.GET)
	public String toDisconnect(Model model,HttpServletRequest request) {
		HttpSession session =request.getSession();
		session.invalidate();
		return this.prepareConnexion(model);
	}
}
