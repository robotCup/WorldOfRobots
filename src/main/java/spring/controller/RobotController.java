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
			//date création
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
	public String prepareAddRobot(Model model){
		
		List<Technology> technologies = this.robotService.findAllTechnologies();

		model.addAttribute("technologies", technologies);
		model.addAttribute("AddRobot", new AddRobot());
		return "addRobot";
	}

	@RequestMapping(value="/robots/add", method = RequestMethod.POST)
	public String toAddRobot(Model model,@ModelAttribute ("AddRobot") AddRobot addRobot, @RequestParam("image") MultipartFile file,HttpServletRequest request){
		
		User user = (User) request.getSession().getAttribute("user");
		
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
				model.addAttribute("robot",this.robotService.findById(robot.getId()));
			} catch (IOException e) {
				e.printStackTrace();			
			}
		}
		else{
			Robot robot = this.robotService.createRobot(addRobot.getTechnologies(), addRobot.getStrong_point(), addRobot.getName(),addRobot.getCreation_date(), null);
			this.userService.createRobot(user.getId(), robot.getId());
			model.addAttribute("robot" , this.robotService.findById(robot.getId()));
		}
		return "robot";
	}

	@RequestMapping(value="/robots/card", method = RequestMethod.GET)
	public String cardRobot(Model model,@RequestParam(value="id") final int id){
		
		Robot robot = this.robotService.findById(id);
		String list_technologies = "";
		
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
		
		model.addAttribute("technologies", list_technologies);
		model.addAttribute("name_file", name_file);
		model.addAttribute("robot", robot);
		return "robot";
	}

}