package spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.model.User;
import spring.model.UserDAO;



@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired private UserDAO utilisateurService;

	@RequestMapping(value="toConnect", method=RequestMethod.GET)
	public String prepareConnexion(Model model) {
		model.addAttribute("connexion", new Connexion());
		return "connexion";
	}
	
	@RequestMapping(value="toConnect", method = RequestMethod.POST)
	public String toConnect(@ModelAttribute ("connexion") Connexion connexion, Model model,HttpServletRequest request) {//page apr�s la connexion
		User user = this.utilisateurService.findByLogin(connexion.getLogin(), connexion.getPwd());
		if (user==null || (!(user.getLogin().equals(connexion.getLogin()))&& !(user.getPwd().equals(connexion.getPwd())))){
			return "connexion";
		}
		else{
			HttpSession session =request.getSession();
			session.setAttribute("login", connexion.getLogin());
			return "competitions";
		}
	}

	@RequestMapping(value="mySpace", method=RequestMethod.GET)
	public String monEspace(Model model,HttpServletRequest request) {
		HttpSession session =request.getSession();
		if(session.getAttribute("login")!=null){
			return "competitions";
		}
		else {
			return this.prepareConnexion(model);
		}
	}
}
