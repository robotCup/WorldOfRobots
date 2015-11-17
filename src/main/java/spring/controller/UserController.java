package spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import spring.model.Competition;

import spring.model.User;

import spring.service.CompetitionService;
import spring.service.UserService;



@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired private UserService utilisateurService;
	@Autowired private CompetitionService competitionService;

	@RequestMapping(value="toConnect", method=RequestMethod.GET)
	public String prepareConnexion(Model model) {
		
		model.addAttribute("connexion", new Connexion());
		model.addAttribute("register", new Register());
		return "connexion";
	}
	
	@RequestMapping(value="toConnect", method = RequestMethod.POST)
	public String toConnect(@ModelAttribute ("connexion") Connexion connexion, Model model,HttpServletRequest request) {//page apr�s la connexion
		User user = this.utilisateurService.findByLogin(connexion.getLogin(), connexion.getPwd());
		if (user == null || (!(user.getLogin().equals(connexion.getLogin())) && !(user.getPwd().equals(connexion.getPwd())))){
			request.setAttribute("testConnexion", false);
			return this.prepareConnexion(model);
		}
		else{
			HttpSession session =request.getSession();
			//session.setAttribute("login", connexion.getLogin());
			session.setAttribute("user", user);
			List<Competition> competitions = competitionService.findAll();
			model.addAttribute("competitions", competitions);
			return "competitions";
		}
	}
	
	@RequestMapping(value="toRegister", method = RequestMethod.POST)
	public String toRegister(@ModelAttribute ("register") Register register, Model model,HttpServletRequest request) {//page apr�s la connexion
		
		try{
		this.utilisateurService.createUser(register.getLogin(),register.getPwd(),register.getEmail());
		model.addAttribute("testInscription", false);
		return this.prepareConnexion(model);
		}
		catch(Exception e){
			request.setAttribute("testInscription", false);
			return this.prepareConnexion(model);
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
	
	/*@RequestMapping(value="contact", method=RequestMethod.GET)
	public String contact(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") != null){
			return "contact";
		}
		else {
			return this.prepareConnexion(model);
		}
	}*/
	
	@RequestMapping(value="disconnect", method=RequestMethod.GET)
	public String toDisconnect(Model model,HttpServletRequest request) {
		HttpSession session =request.getSession();
		session.invalidate();
		return this.prepareConnexion(model);
	}
}
