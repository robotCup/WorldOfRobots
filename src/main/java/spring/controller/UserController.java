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

	/*@RequestMapping(value="/connexion", method = RequestMethod.GET)
    public String connexion(Model model) {//aller � la page de connexion
        return "connexion";
    }*/
	
	
	@RequestMapping(value="toConnect", method=RequestMethod.GET)
    public String prepareConnexion(Model model) {
        model.addAttribute("connexion", new Connexion());
        this.utilisateurService.findAll();
        return "connexion";
    }
	@RequestMapping(value="toConnect", method = RequestMethod.POST)
    public String toConnect(@ModelAttribute ("connexion") Connexion connexion, Model model,HttpServletRequest request) {//page apr�s la connexion
		//model.addAttribute("person", this.utilisateurService.findAll());
		List user =this.utilisateurService.findByLogin(connexion.getLogin(),connexion.getPwd());
		if (user.size()<1){
		//if (!connexion.getLogin().equals("toto")){
       

		 return "connexion";
		}
		else{
			HttpSession session =request.getSession();
			session.setAttribute("login", connexion.getLogin());
		return "competitions";}
    }
	
	@RequestMapping(value="monEspace", method=RequestMethod.GET)
    public String monEspace(Model model,HttpServletRequest request) {
        //model.addAttribute("connexion", new Connexion());
		HttpSession session =request.getSession();
		if(session.getAttribute("login")!=null){
			return "competitions";
		}
		else {
        return this.prepareConnexion(model);
		}
    }
	
	
	
}
