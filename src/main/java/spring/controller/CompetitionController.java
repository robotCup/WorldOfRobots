package spring.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import spring.model.Battle;
import spring.model.Competition;
import spring.model.CompetitionDate;
import spring.model.Robot;
import spring.model.RobotCompetition;
import spring.model.User;
import spring.model.UserCompetitionDate;
import spring.service.CheckUpdatePassword;
import spring.service.CompetitionService;
import spring.service.DeleteCompetition;
import spring.service.RobotService;
import spring.service.UserService;

@Controller
@RequestMapping("/")
public class CompetitionController {

	@Autowired
	private CompetitionService competitionService;
	@Autowired
	private UserService userService;
	@Autowired
	private RobotService robotService;

	@RequestMapping(method = RequestMethod.GET)
	public String Index(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Competition> competitions = competitionService.findAllFuture();
		model.addAttribute("competitions", competitions);
		session.setAttribute("user", user);
		Date datePwd;

		if (user == null) {
			datePwd = new Date();
		} else {
			datePwd = user.getLast_date_pwd();
		}
		model.addAttribute("lastDatePwd", datePwd);
		return "home";
	}

	@RequestMapping(value = "/competitions", method = RequestMethod.GET)
	public String CompetitionsFuture(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Competition> competitions = competitionService.findAllFuture();

		// liste de dates au format francais
		Map<Integer, String> french_dates_start = new HashMap<Integer, String>();
		// liste de dates au format francais
		Map<Integer, String> french_dates_end = new HashMap<Integer, String>();

		for (Competition competition : competitions) {
			if (competition.getEnd_date() == null && competition.getStart_date() == null) {
				french_dates_start.put(competition.getId(), "A définir");
				french_dates_end.put(competition.getId(), "A définir");
			} else {
				french_dates_start.put(competition.getId(),
						new SimpleDateFormat("dd/MM/yyyy HH:mm").format(competition.getStart_date()));
				french_dates_end.put(competition.getId(),
						new SimpleDateFormat("dd/MM/yyyy HH:mm").format(competition.getEnd_date()));
			}
		}
		model.addAttribute("dates_start", french_dates_start);
		model.addAttribute("dates_end", french_dates_end);
		model.addAttribute("title", "Compétitions à venir");
		model.addAttribute("competitions", competitions);
		session.setAttribute("user", session.getAttribute("user"));
		return "competitions";
	}

	@RequestMapping(value = "/competitions/past", method = RequestMethod.GET)
	public String CompetitionsPast(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Competition> competitions = competitionService.findAllPast();

		// liste de dates au format francais
		Map<Integer, String> french_dates_start = new HashMap<Integer, String>();
		// liste de dates au format francais
		Map<Integer, String> french_dates_end = new HashMap<Integer, String>();
		// liste des noms des organisateur
		Map<Integer, String> users_names = new HashMap<Integer, String>();
		// liste des noms des organisateur
		Map<Integer, Long> nb_fighters = new HashMap<Integer, Long>();

		for (Competition competition : competitions) {
			// dates
			french_dates_start.put(competition.getId(),
					new SimpleDateFormat("dd/MM/yyyy HH:mm").format(competition.getStart_date()));
			french_dates_end.put(competition.getId(),
					new SimpleDateFormat("dd/MM/yyyy HH:mm").format(competition.getEnd_date()));

			// organisateur
			User user_competition = this.userService.findById(competition.getId_user());
			users_names.put(competition.getId(), user_competition.getLogin());

			// nb de participants
			nb_fighters.put(competition.getId(), this.competitionService.getNbFighters(competition.getId()));
		}
		model.addAttribute("dates_start", french_dates_start);
		model.addAttribute("dates_end", french_dates_end);
		model.addAttribute("users_names", users_names);
		model.addAttribute("fighters", nb_fighters);
		model.addAttribute("title", "Compétitions terminées");
		model.addAttribute("competitions", competitions);
		session.setAttribute("user", user);
		return "pastCompetitions";
	}

	@RequestMapping(value = "/competitions/myCompetitions", method = RequestMethod.GET)
	public String myCompetitions(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			model.addAttribute("connexion", new Connexion());
			model.addAttribute("register", new Register());
			return "connexion";
		}

		List<Competition> competitions = competitionService.findAllMyCompetitions(user.getId());

		// liste de dates au format francais
		Map<Integer, String> french_dates_start = new HashMap<Integer, String>();
		// liste de dates au format francais
		Map<Integer, String> french_dates_end = new HashMap<Integer, String>();

		for (Competition competition : competitions) {
			if (competition.getEnd_date() == null && competition.getStart_date() == null) {
				french_dates_start.put(competition.getId(), "A d�finir");
				french_dates_end.put(competition.getId(), "A d�finir");
			} else {
				french_dates_start.put(competition.getId(),
						new SimpleDateFormat("dd/MM/yyyy HH:mm").format(competition.getStart_date()));
				french_dates_end.put(competition.getId(),
						new SimpleDateFormat("dd/MM/yyyy HH:mm").format(competition.getEnd_date()));
			}
		}

		model.addAttribute("dates_start", french_dates_start);
		model.addAttribute("dates_end", french_dates_end);
		model.addAttribute("title", "Mes comp�titions");
		model.addAttribute("competitions", competitions);
		session.setAttribute("user",user);
		return "competitions";
	}

	@RequestMapping(value = "/competitions/add", method = RequestMethod.GET)
	public String prepareToAdd(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("connexion", new Connexion());
			model.addAttribute("register", new Register());
			return "connexion";
		} else {
			session.setAttribute("user", user);
			model.addAttribute("add", new AddCompetition());
			return "addCompetition";
		}
	}

	@RequestMapping(value = "/competitions/toAdd", method = RequestMethod.POST)
	public String toAdd(@ModelAttribute("add") AddCompetition cardCompetition, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			request.setAttribute("result", false);
			model.addAttribute("message", "Veuillez vous connecter avant d'ajouter une comp�tition");
			model.addAttribute("connexion", new Connexion());
			model.addAttribute("register", new Register());
			return "connexion";
		} else {
			try {
				if (cardCompetition.getDate_start() != "") {
					
					DateFormat formatter;
					formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date date_s = (Date) formatter.parse(cardCompetition.getDate_start());
					Date dateNow = new Date();
					if (date_s.before(dateNow) || date_s.compareTo(dateNow)==0){
						throw new Exception ("Date incorrecte, veuillez saisir une date sup�rieure � celle d'aujourd'hui");
					}

					Competition competition =this.competitionService.createCompetition(user.getId(), user.getId_robot(),
							cardCompetition.getName(), cardCompetition.getDescription(),
							cardCompetition.getDate_start(), cardCompetition.getRobot_max(),
							cardCompetition.getAddress(), cardCompetition.getGeolocation(),
							cardCompetition.getDuration(), "", "", "", "");
					Timer timer = new Timer();
					timer.schedule(new DeleteCompetition(competition.getId(),this.competitionService, this.userService), new Date(competition.getStart_date().getTime() ));
					System.out.println(new Date(competition.getStart_date().getTime() ));
				} else if (cardCompetition.getDate_start_1() != "" && (cardCompetition.getDate_start_2() != ""
						|| cardCompetition.getDate_start_3() != "" || cardCompetition.getDate_start_4() != "")) {
					
							DateFormat formatter;
							formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
							Date date_1 = (Date) formatter.parse(cardCompetition.getDate_start_1());
							Date date_2 = (Date) formatter.parse(cardCompetition.getDate_start_2());
							Date date_3 = (Date) formatter.parse(cardCompetition.getDate_start_3());
							Date date_4 = (Date) formatter.parse(cardCompetition.getDate_start_4());
	
							Date dateNow = new Date();
							if (date_1.before(dateNow) || date_1.compareTo(dateNow)==0 || date_2.before(dateNow) || date_2.compareTo(dateNow)==0 || date_3.before(dateNow) || date_3.compareTo(dateNow)==0 || date_4.before(dateNow) || date_4.compareTo(dateNow)==0){
								throw new Exception ("Date incorrecte, veuillez saisir une date sup�rieure � celle d'aujourd'hui");
							}
							Competition competition=this.competitionService.createCompetition(user.getId(), user.getId_robot(),
							cardCompetition.getName(), cardCompetition.getDescription(), "",
							cardCompetition.getRobot_max(), cardCompetition.getAddress(),
							cardCompetition.getGeolocation(), cardCompetition.getDuration(),
							cardCompetition.getDate_start_1(), cardCompetition.getDate_start_2(),
							cardCompetition.getDate_start_3(), cardCompetition.getDate_start_4());
				//timer
					Timestamp date = this.competitionService.findMinCompetitionDateByIdCompetition(competition.getId());
					Timer timer = new Timer();
					timer.schedule(new DeleteCompetition(competition.getId(),this.competitionService, this.userService), new Date(date.getTime()));
					System.out.println(new Date(date.getTime()));
				}
				request.setAttribute("result", true);
				model.addAttribute("message", "L'ajout de votre compétition a bien été enregistrée");
			} catch (Exception e) {
				System.out.println(e);
				request.setAttribute("result", false);
				if (e.getMessage().equals("Date incorrect, veuillez saisir une date supérieure à celle d'aujourd'hui")){
					model.addAttribute("message", "Date incorrecte, veuillez saisir une date sup�rieure à celle d'aujourd'hui");
				}
				else {
				model.addAttribute("message", "L'ajout de votre comp�tition a �chou�");
				}
			}
			session.setAttribute("user", user);
			return this.prepareToAdd(model, request);
		}
	}

	@RequestMapping(value = "/competitions/card", method = RequestMethod.GET)
	public String cardCompetition(Model model, @RequestParam(value = "id") final int id, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Competition competition = competitionService.findById(id);

		if (user == null) {
			request.setAttribute("isParticiped", false);
		} else {
			UserCompetitionDate user_competition_date = competitionService.findVote(competition.getId(), user.getId());

			if (user.getId_robot() == 0 || user.getId() == competition.getId_user()) {
				request.setAttribute("isParticiped", false);
			} else {
				Boolean isParticiped = competitionService.isParticiped(user.getId(), competition.getId());
				request.setAttribute("isParticiped", isParticiped);
			}

			if (competition.getStart_date() == null && competition.getEnd_date() == null
					&& user_competition_date == null) {
				request.setAttribute("vote", true);
			} else {
				request.setAttribute("vote", false);
			}
			if (user.getId() == competition.getId_user() && competition.getClose_vote() == false && competition.getStart_date() == null && competition.getEnd_date() == null) {
				request.setAttribute("cloture_vote", false);
			} else {
				request.setAttribute("cloture_vote", true);
			}
			if (user.getId() == competition.getId_user()) {
				request.setAttribute("creator_battle", true);
			} else {
				request.setAttribute("creator_battle", false);
			}
		}
		// liste de dates au format francais
		Map<Integer, String> french_date = new HashMap<Integer, String>();
		
		for(Battle battle : competition.getBattles()){
			french_date.put(battle.getId(), new SimpleDateFormat("dd/MM/yyyy HH:mm").format(battle.getDate()));
		}
		Map<Integer, String> list_name_users = new HashMap<Integer, String>();
		
		List<User> users = this.userService.findAll();
		for(User user_name : users){
			list_name_users.put(user_name.getId(), user_name.getLogin());
		}
		
		request.setAttribute("id_user_competition", competition.getId_user());
		request.setAttribute("boolean_inscription", competition.getClose_participate());
		session.setAttribute("user", user);
		model.addAttribute("french_date", french_date);
		model.addAttribute("users", list_name_users);
		model.addAttribute("competition", competition);
		model.addAttribute("robots", this.robotService.findRobotByIdCompetition(competition.getId()));
		return "competition";
	}

	@RequestMapping(value = "/competitions/participate", method = RequestMethod.GET)
	public String participeCompetition(Model model, @RequestParam(value = "id") final int id,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Competition competition = competitionService.findById(id);

		if (user == null) {
			request.setAttribute("result", false);
			model.addAttribute("message", "Veuillez vous connecter avant de participer à une compétition");
			model.addAttribute("connexion", new Connexion());
			model.addAttribute("register", new Register());
			return "connexion";
		} else {
			this.competitionService.toParticipate(user.getId_robot(), competition.getId());
			session.setAttribute("user", user);
			return "competition";
		}
	}

	@RequestMapping(value = "/competition/toAddBattles", method = RequestMethod.GET)
	public String prepareBattles(Model model, HttpServletRequest request, @RequestParam(value = "id") final int id) {
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		model.addAttribute("addBattles", new AddBattles());

		Competition competition = this.competitionService.findById(id);
		model.addAttribute("competition", competition);

		List<RobotCompetition> robotCompetitions = this.competitionService
				.findRobotCompetitionById(competition.getId());
		request.setAttribute("nbBattles", robotCompetitions.size() / 2);
		model.addAttribute("nbParticipant", robotCompetitions.size());
		session.setAttribute("user", user);
		return "addBattles";
	}
	

	@RequestMapping(value = "/competition/toAddBattles", method = RequestMethod.POST)
	public String addBattles(@ModelAttribute("addBattles") AddBattles addBattles, Model model,
			HttpServletRequest request, HttpServletResponse reponse ) throws IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			request.setAttribute("result", false);
			model.addAttribute("message", "Veuillez vous connecter avant de clôturer la compétition");
			model.addAttribute("connexion", new Connexion());
			model.addAttribute("register", new Register());
		return "connexion";
		} else {
			List<Robot> robots = this.robotService.findRobotByIdCompetition(addBattles.getIdCompetition());
			this.competitionService.createBattles(addBattles.getIdCompetition(), addBattles.getNbMatch(),
					addBattles.getDatesBattles(), addBattles.getNbEquipes(), robots);
			this.competitionService.closeParticipate(this.competitionService.findById(addBattles.getIdCompetition()));
			request.setAttribute("result", true);
			model.addAttribute("message",
					"La clôture des inscriptions et la création des combats de votre compétition ont bien été prises en compte");
			session.setAttribute("user", user);		
			List<User> users = this.competitionService.findUserByCompetition(addBattles.getIdCompetition());
			Competition competition =  this.competitionService.findById(addBattles.getIdCompetition());
			String message="";
			for(User user_compet : users){
				message = "Les combats de la compétition "+competition.getName()+ " ont été établi.";
				this.userService.createMessage(user_compet.getId(), message);
				message = "Les inscriptions ont été clôturées pour la compétition "+competition.getName();
				this.userService.createMessage(user_compet.getId(), message);
			}

			return this.cardCompetition(model, addBattles.getIdCompetition(), request);
		}

	}

	@RequestMapping(value = "/competitions/closeVote", method = RequestMethod.GET)
	public String closeVote(Model model, @RequestParam(value = "id") final int id, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			request.setAttribute("result", false);
			model.addAttribute("message", "Veuillez vous connecter avant de clôturer la compétition");
			model.addAttribute("connexion", new Connexion());
			model.addAttribute("register", new Register());
			return "connexion";
		} 
		else {
			Competition competition = this.competitionService.findById(id);
			session.setAttribute("user", user);
			
			if (competition == null) {
				request.setAttribute("result", false);
				model.addAttribute("message", "Le compotition séléctionner n'est pas reconnue");
				return this.myCompetitions(model, request);
			} 
			
			else {
				this.competitionService.closeVote(competition);
				List<User> users = this.competitionService.findUserByCompetition(competition.getId());
				
				for(User user_compet : users){
					String message = "Les votes ont été cloturés pour la compotition "+competition.getName()+ "";
					this.userService.createMessage(user_compet.getId(), message);
				}
				return this.cardCompetition(model, id, request);
			}
		}

	}

	@RequestMapping(value = "/competitions/vote", method = RequestMethod.GET)
	public String prepareVote(Model model, @RequestParam(value = "id") final int id, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			request.setAttribute("result", false);
			model.addAttribute("message", "Veuillez vous connecter avant de consulter les votes");
			model.addAttribute("connexion", new Connexion());
			model.addAttribute("register", new Register());
			return "connexion";
		} else {
			Map<Integer, String> french_date = new HashMap<Integer, String>();
			List<CompetitionDate> dates = competitionService.findAllDates(id);
			VoteCompetition voteCompetiton = new VoteCompetition();

			for (CompetitionDate date : dates) {
				french_date.put(date.getId(), new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date.getDate()));
			}

			model.addAttribute("voteCompetiton", voteCompetiton);
			model.addAttribute("french_date", french_date);
			model.addAttribute("dates", dates);
			session.setAttribute("user", user);
			return "voteCompetition";
		}
	}

	@RequestMapping(value = "/competitions/vote", method = RequestMethod.POST)
	public String vote(@ModelAttribute("vote") VoteCompetition voteCompetition, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		session.setAttribute("user", user);

		if (user == null) {
			request.setAttribute("result", false);
			model.addAttribute("message", "Veuillez vous connecter avant d'ajouter une compétition");
			model.addAttribute("connexion", new Connexion());
			model.addAttribute("register", new Register());
			return "connexion";
		} else {
			this.competitionService.vote(this.competitionService.findCompetitionDate(voteCompetition.getChoose_date()),
					user.getId());

			List<Competition> competitions = competitionService.findAllFuture();
			model.addAttribute("competitions", competitions);
			session.setAttribute("user", user);

			request.setAttribute("result", true);
			model.addAttribute("message", "Votre vote a bien été pris en compte");

			return "competitions";
		}
	}

	@RequestMapping(value = "/competition/win", method = RequestMethod.GET)
	public void winnerBattle(Model model, HttpServletRequest request, @RequestParam(value = "id") final int id,
			@RequestParam(value = "idBattle") final int idBattle) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		session.setAttribute("user", user);
		this.competitionService.winnerBattle(this.competitionService.getBattleById(idBattle), id);
		
	}
	
	@RequestMapping(value = "/competition/winCompetition", method = RequestMethod.GET)
	public void winnerCompetition(Model model, HttpServletRequest request, @RequestParam(value = "id") final int id,
			@RequestParam(value = "idCompetition") final int idCompetition) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		session.setAttribute("user", user);
		this.competitionService.winnerCompetition(this.competitionService.findById(idCompetition), id);
		
	}
}