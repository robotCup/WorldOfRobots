package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/robots")
public class RobotController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String Robots(){
		return "robots";
	}
	
}