package spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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
import spring.service.RobotService;


@Controller
public class RobotController {

	@Autowired private RobotService robotService;

	@RequestMapping(value="/robots", method = RequestMethod.GET)
	public String Robots(Model model){
		List <Robot> robots = this.robotService.findAll();
		model.addAttribute("robots", robots);
		return "robots";
	}

	@RequestMapping(value="/robots/add", method = RequestMethod.GET)
	public String prepareAddRobot(Model model){
		model.addAttribute("AddRobot", new AddRobot());
		return "addRobot";
	}

	@RequestMapping(value="/robots/add", method = RequestMethod.POST)
	public String toAddRobot(Model model,@ModelAttribute ("AddRobot") AddRobot addRobot, @RequestParam("image") MultipartFile file,HttpServletRequest request){
		
		if (!addRobot.getImage().isEmpty()) {
			byte[] bytes;
			try {
				bytes = addRobot.getImage().getBytes();


				File serverFile = new File("D:/projet_git/WorldOfRobots/src/main/webapp/resources/images/"+addRobot.getName()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));

				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);  
				stream.close();       

				Robot robot=this.robotService.createRobot(addRobot.getName(),addRobot.getCreation_date(),addRobot.getName()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));

				model.addAttribute("robot",this.robotService.findById(robot.getId()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "robot";
	}

	@RequestMapping(value="/robots/card", method = RequestMethod.GET)
	public String cardRobot(Model model,@RequestParam(value="id") final int id){
		Robot robot = this.robotService.findById(id);
		model.addAttribute("robot", robot);
		return "robot";
	}

}