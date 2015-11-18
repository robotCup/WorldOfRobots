package spring.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import spring.model.Robot;
import spring.model.RobotDAO;


@Service
public class RobotService {

	
	@Autowired private RobotDAO robotDAO;
	
	@Transactional(readOnly=true)
	public List<Robot> findAll() {
		return this.robotDAO.findAll();
	}

	@Transactional(readOnly=true)
	public Robot findById(int id) {
		return this.robotDAO.findById(id);
	}
	
	@Transactional
	public Robot createRobot(String name, String creation_date, String string) {
		return this.robotDAO.createRobot(name,creation_date,string);
		
	}

}
