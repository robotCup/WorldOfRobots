package spring.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import spring.model.Robot;
import spring.model.RobotDAO;
import spring.model.Technology;


@Service
public class RobotService {

	
	@Autowired private RobotDAO robotDAO;
	
	@Transactional(readOnly=true)
	public List<Robot> findAll() {
		return this.robotDAO.findAll();
	}
	
	@Transactional(readOnly=true)
	public List<Technology> findAllTechnologies() {
		return this.robotDAO.findAllTechnologies();
	}
	
	@Transactional(readOnly=true)
	public Robot findById(int id) {
		return this.robotDAO.findById(id);
	}
	
	@Transactional
	public Robot createRobot(List<Integer> technologies, String strong_point, String name, String creation_date, String image) {
		return this.robotDAO.createRobot(technologies, strong_point, name, creation_date, image);
		
	}

}
