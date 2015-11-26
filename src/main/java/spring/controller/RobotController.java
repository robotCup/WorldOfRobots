package spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import spring.model.Robot;
import spring.model.Technology;
import spring.model.User;
import spring.service.RobotService;
import spring.service.UserService;


@Controller
public class RobotController {

	@Autowired private RobotService robotService;
	@Autowired private UserService userService;

	@RequestMapping(value="/robots", method = RequestMethod.GET)
	public String Robots(Model model){
		List <Robot> robots = this.robotService.findAll();

		//liste de dates au format francais
		Map<Integer, String> french_dates = new HashMap<Integer, String>();
		//slider d'images
		ArrayList<String> list_images = new ArrayList<String>();

		for(Robot robot : robots){
			//images
			File image = new File("D:/projet_git/WorldOfRobots/src/main/webapp/resources/images/robots/"+robot.getPath_picture());

			if(image.exists() && !image.isDirectory()){
				list_images.add(robot.getPath_picture());
			}
			//date crÃ©ation
			french_dates.put(robot.getId(), new SimpleDateFormat("dd/MM/yyyy HH:mm").format(robot.getCreation_date()));
		}
		if(list_images.isEmpty()){
			list_images.add("no-image.png");
		}

		model.addAttribute("list_images", list_images);
		model.addAttribute("dates", french_dates);
		model.addAttribute("robots", robots);
		return "robots";
	}

	@RequestMapping(value="/robots/add", method = RequestMethod.GET)
	public String prepareAddRobot(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user!=null){
			List<Technology> technologies = this.robotService.findAllTechnologies();
			model.addAttribute("technologies", technologies);
			model.addAttribute("AddRobot", new AddRobot());
			return "addRobot";
		}
		else {
			request.setAttribute("result", false);
			model.addAttribute("message", "Veuillez vous connecter avant d'ajouter un robot");
			model.addAttribute("connexion", new Connexion());
			model.addAttribute("register", new Register());
			return "connexion";
		}
	}

	@RequestMapping(value="/robots/add", method = RequestMethod.POST)
	public String toAddRobot(Model model,@ModelAttribute ("AddRobot") AddRobot addRobot, @RequestParam("image") MultipartFile file,HttpServletRequest request){

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user!=null){
			if (!addRobot.getImage().isEmpty()) {
				byte[] bytes;
				try {
					bytes = addRobot.getImage().getBytes();

					String name_file = addRobot.getName()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
					name_file = name_file.replace(' ', '_');

					File server_file = new File("D:/projet_git/WorldOfRobots/src/main/webapp/resources/images/robots/"+name_file);

					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(server_file));
					stream.write(bytes);  
					stream.close();

					Robot robot = this.robotService.createRobot(addRobot.getTechnologies(), addRobot.getStrong_point(), addRobot.getName(),addRobot.getCreation_date(), name_file);
					this.userService.createRobot(user.getId(), robot.getId());

					request.setAttribute("result", true);
					model.addAttribute("message", "L'ajout de votre robot a bien été enregistrée");
					model.addAttribute("robot",this.robotService.findById(robot.getId()));

				} catch (IOException e) {
					e.printStackTrace();
					request.setAttribute("result", false);
					model.addAttribute("message", "L'ajout de votre robot a échoué");
				}
			}
			else{
				Robot robot = this.robotService.createRobot(addRobot.getTechnologies(), addRobot.getStrong_point(), addRobot.getName(),addRobot.getCreation_date(), null);

				if(robot != null){
					session.setAttribute("user", user);
					this.userService.createRobot(user.getId(), robot.getId());
					model.addAttribute("robot" , this.robotService.findById(robot.getId()));

					request.setAttribute("result", true);
					model.addAttribute("message", "L'ajout de votre robot a bien été enregistrée");
					session.setAttribute("user", user);
				}
				else{
					request.setAttribute("result", false);
					model.addAttribute("message", "L'ajout de votre robot a échoué");
				}

			}
			return "robot";
		}
		else {
			request.setAttribute("result", false);
			model.addAttribute("message", "Veuillez vous connecter avant d'ajouter un robot");
			model.addAttribute("connexion", new Connexion());
			model.addAttribute("register", new Register());
			return "connexion";
		}
	}
	@RequestMapping(value="/robots/join", method = RequestMethod.GET)
	public String joinRobot(Model model,@RequestParam(value="id") final int id, HttpServletRequest request){

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user!=null){
			this.robotService.joinRobot(id, user);
			session.setAttribute("user", user);
			request.setAttribute("result", true);
			model.addAttribute("message", "Vous faites désormais partie d'une équipe");
			session.setAttribute("user", user);
			return this.cardRobot(model, id, request);
		}
		else {
			request.setAttribute("result", false);
			model.addAttribute("message", "Veuillez vous connecter avant de rejoindre une équipe");
			model.addAttribute("connexion", new Connexion());
			model.addAttribute("register", new Register());
			return "connexion";
		}
	}
	@RequestMapping(value="/robots/card", method = RequestMethod.GET)
	public String cardRobot(Model model,@RequestParam(value="id") final int id,HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Robot robot = this.robotService.findById(id);
		String list_technologies = "";
		//liste de dates au format francais
		Map<Integer, String> french_dates = new HashMap<Integer, String>();

		for(int i= 0 ; i < robot.getTechnologies().size(); i++ ){

			list_technologies += robot.getTechnologies().get(i).getName();
			if(robot.getTechnologies().size()-1 == i){
				list_technologies += ".";
			}
			else{
				list_technologies += ", ";
			}
		}

		File image = new File("D:/projet_git/WorldOfRobots/src/main/webapp/resources/images/robots/"+robot.getPath_picture());
		String name_file = "";

		if(image.exists() && !image.isDirectory()){
			name_file = image.getName();
		}
		else{
			name_file = "no-image.png";
		}

		french_dates.put(robot.getId(), new SimpleDateFormat("dd/MM/yyyy HH:mm").format(robot.getCreation_date()));

		Long participate_competition = this.robotService.countCompetitionByRobot(robot.getId());
		Long participate_battle = this.robotService.countBattleByRobot(robot.getId());
		Long win_battle = this.robotService.countWinBalttleByRobot(robot.getId());

		model.addAttribute("technologies", list_technologies);
		model.addAttribute("name_file", name_file);
		model.addAttribute("participate_competition", participate_competition);
		model.addAttribute("participate_battle", participate_battle);
		model.addAttribute("win", win_battle);
		model.addAttribute("lose", (participate_battle - win_battle));
		model.addAttribute("robot", robot);
		model.addAttribute("french_dates", french_dates);
		session.setAttribute("user", user);
		return "robot";
	}

}