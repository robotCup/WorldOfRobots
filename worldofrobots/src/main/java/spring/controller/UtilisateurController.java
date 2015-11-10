package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.modele.UtilisateurDAO;





@Controller
@RequestMapping("/connexion")
public class UtilisateurController {
	
	@Autowired private UtilisateurDAO utilisateurService;

	@RequestMapping(method = RequestMethod.GET)
    public String connexion(Model model) {
		model.addAttribute("person", this.utilisateurService.findAll());
       

        return "connexion";
    }
}
