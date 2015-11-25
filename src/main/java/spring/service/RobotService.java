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
import spring.model.User;


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
	public Robot createRobot(List<String> technologies, String strong_point, String name, String creation_date, String image) {
		return this.robotDAO.createRobot(technologies, strong_point, name, creation_date, image);
		
	}
	
	@Transactional
	public List<Robot> findRobotByIdCompetition(int idCompetition) {
		// TODO Auto-generated method stub
		return  this.robotDAO.findRobotByIdCompetition(idCompetition);
	}
	
	@Transactional
	public Long countBattleByRobot(int id_robot) {
		// TODO Auto-generated method stub
		return this.robotDAO.countBattleByRobot(id_robot);
	}
	
	@Transactional
	public Long countWinBalttleByRobot(int id_robot) {
		// TODO Auto-generated method stub
		return this.robotDAO.countWinBalttleByRobot(id_robot);
	}
	
	@Transactional
	public Long countCompetitionByRobot(int id_robot) {
		// TODO Auto-generated method stub
		return this.robotDAO.countCompetitionByRobot(id_robot);
	}

	@Transactional
	public void joinRobot(int id_robot, User user) {
		// TODO Auto-generated method stub
		this.robotDAO.joinRobot(id_robot, user);
	}
}
