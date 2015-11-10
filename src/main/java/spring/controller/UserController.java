package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String greetingForm(Model model) {
        model.addAttribute("connexion", new Connexion());
        this.utilisateurService.findAll();
        return "connexion";
    }
	@RequestMapping(value="toConnect", method = RequestMethod.POST)
    public String toConnect(@ModelAttribute ("connexion") Connexion connexion, Model model) {//page apr�s la connexion
		//model.addAttribute("person", this.utilisateurService.findAll());
		if (this.utilisateurService.findByLogin(connexion.getLogin(),connexion.getPwd())== null){
		//if (!connexion.getLogin().equals("toto")){
       
		this.utilisateurService.findByLogin(connexion.getLogin(),connexion.getPwd());
		 return "connexion";
		}
		else{
		return "competitions";}
    }
}
