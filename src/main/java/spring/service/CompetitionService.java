package spring.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import spring.model.Competition;
import spring.model.CompetitionDAO;
import spring.model.CompetitionDate;
import spring.model.UserCompetitionDate;

@Service
public class CompetitionService {

	@Autowired private CompetitionDAO competitionDAO;
	
	@Transactional(readOnly = true)
	public List<Competition> findAll() {
		return this.competitionDAO.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Competition> findAllFuture() {
		return this.competitionDAO.findAllFuture();
	}
	
	@Transactional(readOnly = true)
	public List<Competition> findAllPast() {
		return this.competitionDAO.findAllPast();
	}
	
	@Transactional(readOnly = true)
	public List<Competition> findAllMyCompetitions(int id_user) {
		return this.competitionDAO.findAllMyCompetitions(id_user);
	}

	@Transactional(readOnly = true)
	public Competition findById(int id) {
		// TODO Auto-generated method stub
		return this.competitionDAO.findByID(id);
	}
	
	@Transactional
	public void createCompetition(int id_user, int id_robot, String name, String desc, String start_date, int robot_max, String address, String geolocation, int duration, String start_date_1, String start_date_2, String start_date_3, String start_date_4)
			throws MySQLIntegrityConstraintViolationException, ConstraintViolationException {
		// TODO Auto-generated method stub
		 this.competitionDAO.createCompetition(id_user, id_robot, name, desc,start_date, robot_max,address, geolocation, duration, start_date_1, start_date_2, start_date_3, start_date_4);
	}
	@Transactional(readOnly = true)
	public long getNbFighters(int id_competition) {
		// TODO Auto-generated method stub
		return this.competitionDAO.getNbFighters(id_competition);
	}
	@Transactional(readOnly = true)
	public Boolean isParticiped(int id_user, int id_competition) {
		return this.competitionDAO.isParticiped(id_user, id_competition);
	}
	@Transactional
	public void toParticipate(int id_robot, int id_competition) {
		// TODO Auto-generated method stub
		this.competitionDAO.toParticipate(id_robot, id_competition);
	}
	@Transactional
	public void closeParticipate(Competition competition) {
		// TODO Auto-generated method stub
		this.competitionDAO.closeParticipate(competition);
	}
	
	@Transactional
	public List<CompetitionDate> findAllDates(int id) {
		// TODO Auto-generated method stub
		return this.competitionDAO.findAllDates(id);
	}
	
	@Transactional
	public void vote(CompetitionDate competition, int id_user) {
		// TODO Auto-generated method stub
		this.competitionDAO.vote(competition, id_user);
	}
	
	@Transactional
	public CompetitionDate findCompetitionDate(int id){
		return this.competitionDAO.findCompetitionDate(id);
	}
	
	@Transactional
	public UserCompetitionDate findVote(int id_competition, int id_user) {
		// TODO Auto-generated method stub
		return this.competitionDAO.findVote(id_competition, id_user);
	}
	
	@Transactional
	public void closeVote(Competition competition) {
		// TODO Auto-generated method stub
		this.competitionDAO.closeVote(competition);
	}
	
}
