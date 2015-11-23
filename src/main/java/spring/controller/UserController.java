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
import spring.model.Robot;
import spring.model.User;

import spring.service.CompetitionService;
import spring.service.RobotService;
import spring.service.UserService;



@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired private UserService utilisateurService;
	@Autowired private CompetitionService competitionService;
	@Autowired private RobotService robotService;

	@RequestMapping(value="/toConnect", method=RequestMethod.GET)
	public String prepareConnexion(Model model) {

		model.addAttribute("connexion", new Connexion());
		model.addAttribute("register", new Register());
		return "connexion";
	}

	@RequestMapping(value="/toConnect", method = RequestMethod.POST)
	public String toConnect(@ModelAttribute ("connexion") Connexion connexion, Model model,HttpServletRequest request) {//page apr�s la connexion

		User user = this.utilisateurService.findByLogin(connexion.getLogin(), connexion.getPwd());

		if (user == null || (!(user.getLogin().equals(connexion.getLogin())) && !(user.getPwd().equals(connexion.getPwd())))){
			request.setAttribute("result", false);
			model.addAttribute("message", "La connexion a échoué");
			return this.prepareConnexion(model);
		}
		else{
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(1000);
			List<Competition> competitions = competitionService.findAllFuture();
			model.addAttribute("competitions", competitions);
			return "home";
		}
	}

	@RequestMapping(value="/toRegister", method = RequestMethod.POST)
	public String toRegister(@ModelAttribute ("register") Register register, Model model,HttpServletRequest request) {

		try{
			if(!register.getPwd().equals(register.getPwd_confirm())){
				request.setAttribute("result", false);
				model.addAttribute("message", "L'inscription a échoué : Veuillez saisir deux fois le même mot de passe");
			}
			else{
				this.utilisateurService.createUser(register.getLogin(),register.getPwd(),register.getEmail());
				model.addAttribute("result", true);
				model.addAttribute("message", "L'inscription a bien été enregistrée");
			}
		}
		catch(Exception e){
			request.setAttribute("result", false);
			model.addAttribute("message", "L'inscription a échoué");
		}
		return this.prepareConnexion(model);
	}


	@RequestMapping(value="/mySpace", method=RequestMethod.GET)
	public String monEspacePrepare(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if(user != null){
			session.setAttribute("user", session.getAttribute("user"));
			Robot robot = null;
			if(user.getId_robot() != 0)
			robot = this.robotService.findById(user.getId_robot());

			model.addAttribute("update", new UpdateUser());
			model.addAttribute("robot", robot);
			model.addAttribute("user", this.utilisateurService.findById(user.getId()));
			session.setAttribute("user", user);
			return "mySpace";
		}
		else {
			return this.prepareConnexion(model);
		}
	}


	@RequestMapping(value="/mySpace", method=RequestMethod.POST)
	public String monEspace(Model model,HttpServletRequest request) {
		HttpSession session =request.getSession();
		User user = (User) session.getAttribute("user");

		if(user != null){
			User userRobot = this.utilisateurService.findById(user.getId());
			Robot robotUser = null;
			session.setAttribute("user", userRobot);
			if(userRobot.getId_robot() != 0){
				robotUser = this.robotService.findById(userRobot.getId_robot());
			}
			else
				robotUser = null;
			model.addAttribute("user", userRobot);
			model.addAttribute("robot", robotUser);
			request.setAttribute("robot", robotUser);
			return "mySpace";
		}
		else {
			request.setAttribute("result", false);
			model.addAttribute("message", "Veuillez vous connecter avant de modifier vos données personnelles");
			return this.prepareConnexion(model);
		}
	}

	@RequestMapping(value="/modifyMySpace", method = RequestMethod.POST)
	public String toModifyMySpace(@ModelAttribute ("update") UpdateUser update, Model model,HttpServletRequest request) {

		try{
			if (this.utilisateurService.findByLogin(update.getLogin(), update.getPwdOld())!=null){
				String pwd= update.getPwd();
				if (update.getPwd().equals("")){
					pwd = update.getPwdOld();
				}
				this.utilisateurService.updateUser(update.getId(),update.getLogin(),pwd,update.getEmail());
				request.setAttribute("result", true);
				model.addAttribute("message", "La modification de vos données personelles a bien été enregistrée");
			}
			else {
				request.setAttribute("result", false);
				model.addAttribute("message", "La prise en compte de vos modifications a échoué");
			}
		}
		catch(Exception e){
			System.out.println(e);
			request.setAttribute("result", false);
			model.addAttribute("message", "La prise en compte de vos modifications a échoué");
		}
		return this.monEspacePrepare(model, request);
	}

	@RequestMapping(value="/disconnect", method=RequestMethod.GET)
	public String toDisconnect(Model model,HttpServletRequest request) {
		HttpSession session =request.getSession();
		session.invalidate();
		return this.prepareConnexion(model);
	}
}
