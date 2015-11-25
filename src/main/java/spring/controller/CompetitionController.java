package spring.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

import spring.model.Battle;
import spring.model.Competition;
import spring.model.CompetitionDate;
import spring.model.Robot;
import spring.model.RobotCompetition;
import spring.model.User;
import spring.model.UserCompetitionDate;
import spring.service.CompetitionService;
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
		model.addAttribute("title", "Mes compétitions");
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
			model.addAttribute("message", "Veuillez vous connecter avant d'ajouter une compétition");
			model.addAttribute("connexion", new Connexion());
			model.addAttribute("register", new Register());
			return "connexion";
		} else {
			try {
				if (cardCompetition.getDate_start() != "") {
					this.competitionService.createCompetition(user.getId(), user.getId_robot(),
							cardCompetition.getName(), cardCompetition.getDescription(),
							cardCompetition.getDate_start(), cardCompetition.getRobot_max(),
							cardCompetition.getAddress(), cardCompetition.getGeolocation(),
							cardCompetition.getDuration(), "", "", "", "");
				} else if (cardCompetition.getDate_start_1() != "" && (cardCompetition.getDate_start_2() != ""
						|| cardCompetition.getDate_start_3() != "" || cardCompetition.getDate_start_4() != "")) {
					this.competitionService.createCompetition(user.getId(), user.getId_robot(),
							cardCompetition.getName(), cardCompetition.getDescription(), "",
							cardCompetition.getRobot_max(), cardCompetition.getAddress(),
							cardCompetition.getGeolocation(), cardCompetition.getDuration(),
							cardCompetition.getDate_start_1(), cardCompetition.getDate_start_2(),
							cardCompetition.getDate_start_3(), cardCompetition.getDate_start_4());
				}
				request.setAttribute("result", true);
				model.addAttribute("message", "L'ajout de votre compétition a bien été enregistrée");
			} catch (Exception e) {
				
				request.setAttribute("result", false);
				model.addAttribute("message", "L'ajout de votre compétition a échoué");
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
			if (user.getId() == competition.getId_user() && competition.getClose_vote() == false) {
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
		
		request.setAttribute("id_user_competition", competition.getId_user());
		request.setAttribute("boolean_inscription", competition.getClose_participate());
		session.setAttribute("user", user);
		model.addAttribute("french_date", french_date);
		model.addAttribute("competition", competition);
		
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

	@RequestMapping(value = "/competitions/closeParticipate", method = RequestMethod.GET)
	public String closeParticipate(Model model, @RequestParam(value = "id") final int id, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			request.setAttribute("result", false);
			model.addAttribute("message", "Veuillez vous connecter avant de clôturer la compétition");
			model.addAttribute("connexion", new Connexion());
			model.addAttribute("register", new Register());
			return "connexion";
		} else {

			this.competitionService.closeParticipate(this.competitionService.findById(id));
			request.setAttribute("result", true);
			model.addAttribute("message",
					"La clôturer des inscriptions de votre compétition a bien été prise en compte");
			session.setAttribute("user", user);
			return this.cardCompetition(model, id, request);
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
			HttpServletRequest request) {

		List<Robot> robots = this.robotService.findRobotByIdCompetition(addBattles.getIdCompetition());
		this.competitionService.createBattles(addBattles.getIdCompetition(), addBattles.getNbMatch(),
				addBattles.getDatesBattles(), addBattles.getNbEquipes(), robots);
		System.out.println(addBattles.getNbEquipes());
		System.out.println(addBattles.getDatesBattles());
		System.out.println(addBattles.getNbMatch());
		System.out.println(addBattles.getIdCompetition());
		return this.Index(model, request);
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
		} else {
			Competition competition = this.competitionService.findById(id);
			session.setAttribute("user", user);
			
			if (competition == null) {
				request.setAttribute("result", false);
				model.addAttribute("message", "Le compétition séléctionner n'est pas reconnue");
				return this.myCompetitions(model, request);
			} 
			
			else {
				this.competitionService.closeVote(competition);
				request.setAttribute("result", true);
				model.addAttribute("message", "La clôture des votes de votre compétition a bien été prise en compte");
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
}