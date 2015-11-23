package spring.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.model.Competition;
import spring.model.Robot;
import spring.model.RobotCompetition;
import spring.model.User;
import spring.service.CompetitionService;
import spring.service.UserService;

@Controller
@RequestMapping("/")
public class CompetitionController {	

	@Autowired private CompetitionService competitionService;
	@Autowired private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String Index(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Competition> competitions = competitionService.findAllFuture();
		model.addAttribute("competitions", competitions);
		session.setAttribute("user", user);
		Date datePwd;
		if (user == null){
			datePwd = new Date();
		}
		else {
			datePwd = user.getLast_date_pwd();
		}
		model.addAttribute("lastDatePwd", datePwd);
		return "home";
	}

	@RequestMapping(value="/competitions", method = RequestMethod.GET)
	public String CompetitionsFuture(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();		
		List<Competition> competitions = competitionService.findAllFuture();

		//liste de dates au format francais
		Map<Integer, String> french_dates_start = new HashMap<Integer, String>();
		//liste de dates au format francais
		Map<Integer, String> french_dates_end = new HashMap<Integer, String>();

		for(Competition competition : competitions){
			if(competition.getEnd_date() == null && competition.getStart_date() == null){
				french_dates_start.put(competition.getId(), "A définir");
				french_dates_end.put(competition.getId(), "A définir");
			}
			else{
				french_dates_start.put(competition.getId(), new SimpleDateFormat("dd/MM/yyyy HH:mm").format(competition.getStart_date()));
				french_dates_end.put(competition.getId(), new SimpleDateFormat("dd/MM/yyyy HH:mm").format(competition.getEnd_date()));
			}			
		}
		model.addAttribute("dates_start", french_dates_start);
		model.addAttribute("dates_end", french_dates_end);
		model.addAttribute("title", "Compétitions à venir");
		model.addAttribute("competitions", competitions);
		session.setAttribute("user", session.getAttribute("user"));
		return "competitions";
	}

	@RequestMapping(value="/competitions/past", method = RequestMethod.GET)
	public String CompetitionsPast(Model model,HttpServletRequest request){

		HttpSession session = request.getSession();		
		List<Competition> competitions = competitionService.findAllPast();

		//liste de dates au format francais
		Map<Integer, String> french_dates_start = new HashMap<Integer, String>();
		//liste de dates au format francais
		Map<Integer, String> french_dates_end = new HashMap<Integer, String>();
		//liste des noms des organisateur
		Map<Integer, String> users_names = new HashMap<Integer, String>();
		//liste des noms des organisateur
		Map<Integer, Long> nb_fighters = new HashMap<Integer, Long>();

		for(Competition competition : competitions){
			//dates
			french_dates_start.put(competition.getId(), new SimpleDateFormat("dd/MM/yyyy HH:mm").format(competition.getStart_date()));
			french_dates_end.put(competition.getId(), new SimpleDateFormat("dd/MM/yyyy HH:mm").format(competition.getEnd_date()));
			
			//organisateur
			User user_competition = this.userService.findById(competition.getId_user());
			users_names.put(competition.getId(), user_competition.getLogin());
			
			//nb de participants
			nb_fighters.put(competition.getId(), this.competitionService.getNbFighters(competition.getId()));
		}
		model.addAttribute("dates_start", french_dates_start);
		model.addAttribute("dates_end", french_dates_end);
		model.addAttribute("users_names", users_names);
		model.addAttribute("fighters", nb_fighters);
		model.addAttribute("title", "Compétitions terminées");
		model.addAttribute("competitions", competitions);
		return "pastCompetitions";
	}
	@RequestMapping(value="/competitions/myCompetitions", method = RequestMethod.GET)
	public String myCompetitions(Model model, HttpServletRequest request){		

		User user = (User) request.getSession().getAttribute("user");
		
		if(user == null){
			return UserController.prepareConnexion(model);
		}
		
		List<Competition> competitions = competitionService.findAllMyCompetitions(user.getId());
		
		//liste de dates au format francais
		Map<Integer, String> french_dates_start = new HashMap<Integer, String>();
		//liste de dates au format francais
		Map<Integer, String> french_dates_end = new HashMap<Integer, String>();

		for(Competition competition : competitions){
			if(competition.getEnd_date() == null && competition.getStart_date() == null){
				french_dates_start.put(competition.getId(), "A définir");
				french_dates_end.put(competition.getId(), "A définir");
			}
			else{
				french_dates_start.put(competition.getId(), new SimpleDateFormat("dd/MM/yyyy HH:mm").format(competition.getStart_date()));
				french_dates_end.put(competition.getId(), new SimpleDateFormat("dd/MM/yyyy HH:mm").format(competition.getEnd_date()));
			}			
		}

		model.addAttribute("dates_start", french_dates_start);
		model.addAttribute("dates_end", french_dates_end);
		model.addAttribute("title", "Mes compétitions");
		model.addAttribute("competitions", competitions);
		return "competitions";
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
			request.setAttribute("result", false);
			model.addAttribute("message", "Veuillez vous connecter avant d'ajouter une compétition");		
			return UserController.prepareConnexion(model);
		}
		else{
			try{
				if(cardCompetition.getDate_start() != ""){
					this.competitionService.createCompetition(user.getId(), user.getId_robot(), cardCompetition.getName(), cardCompetition.getDescription(), cardCompetition.getDate_start(),
							cardCompetition.getRobot_max(), cardCompetition.getAddress(), cardCompetition.getGeolocation(), cardCompetition.getDuration(), "", "", "", "");
				}
				else if(cardCompetition.getDate_start_1() != "" && (cardCompetition.getDate_start_2() != "" || cardCompetition.getDate_start_3() != "" || cardCompetition.getDate_start_4() != "")){
					this.competitionService.createCompetition(user.getId(), user.getId_robot(),cardCompetition.getName(), cardCompetition.getDescription(), cardCompetition.getDate_start(),
							cardCompetition.getRobot_max(), cardCompetition.getAddress(), cardCompetition.getGeolocation(), cardCompetition.getDuration(), cardCompetition.getDate_start_1(), cardCompetition.getDate_start_2(), cardCompetition.getDate_start_3(), cardCompetition.getDate_start_4());
				}
				session.setAttribute("user", user);
				request.setAttribute("result", true);
				model.addAttribute("message", "L'ajout de votre compétition a bien été enregistrée");
				return this.prepareToAdd(model, request);
			}
			catch(Exception e){
				session.setAttribute("user", user);
				request.setAttribute("result", false);
				model.addAttribute("message", "L'ajout de votre compétition a échoué");
				return this.prepareToAdd(model, request);
			}
		}					
	}

	@RequestMapping(value="/competitions/card", method = RequestMethod.GET)
	public String cardCompetition(Model model, @RequestParam(value="id") final int id, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Competition competition = competitionService.findById(id);
		
		if(user == null){
			request.setAttribute("isParticiped", false);	
		}
		else{
			Boolean isParticiped = competitionService.isParticiped(user.getId(), competition.getId());			
			request.setAttribute("isParticiped", isParticiped);		
		}	
		request.setAttribute("id_user_competition", competition.getId_user());
		request.setAttribute("boolean_inscription", competition.getClose_participate());
		session.setAttribute("user", user);
		model.addAttribute("competition", competition);
		return "competition";
	}
	
	@RequestMapping(value="/competitions/participate", method = RequestMethod.GET)
	public String participeCompetition(Model model, @RequestParam(value="id") final int id, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Competition competition = competitionService.findById(id);	
		
		if(user == null){
			request.setAttribute("result", false);
			model.addAttribute("message", "Veuillez vous connecter avant de participer à une compétition");		
			return UserController.prepareConnexion(model);
		}
		else{
			this.competitionService.toParticipate(user.getId_robot(), competition.getId());			
			return "competition";
		}
	}
	
	@RequestMapping(value="/competitions/closeParticipate", method = RequestMethod.GET)
	public String closeParticipate(Model model, @RequestParam(value="id") final int id, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user == null){
			request.setAttribute("result", false);
			model.addAttribute("message", "Veuillez vous connecter avant de clôturer la compétition");		
			return UserController.prepareConnexion(model);
		}
		else{
			
			this.competitionService.closeParticipate(this.competitionService.findById(id));
			request.setAttribute("result", true);
			model.addAttribute("message", "La clôturer des inscriptions de votre compétition a bien été prise en compte");
			return this.cardCompetition(model, id, request);
		}
	}

}