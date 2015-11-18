package spring.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import spring.model.Competition;
import spring.model.CompetitionDAO;

@Service
public class CompetitionService {

	@Autowired private CompetitionDAO competitionDAO;
	
	@Transactional(readOnly = true)
	public List<Competition> findAll() {
		return this.competitionDAO.findAll();
	}

	@Transactional(readOnly=true)
	public Competition findById(int id) {
		// TODO Auto-generated method stub
		return this.competitionDAO.findByID(id);
	}
	
	@Transactional
	public void createCompetition(int id_user, String name, String desc, String start_date, int robot_max, String address, String geolocation, int duration, String start_date_1, String start_date_2, String start_date_3, String start_date_4)
			throws MySQLIntegrityConstraintViolationException, ConstraintViolationException {
		// TODO Auto-generated method stub
		 this.competitionDAO.createCompetition(id_user, name, desc,start_date, robot_max,address, geolocation, duration, start_date_1, start_date_2, start_date_3, start_date_4);
	}

}
