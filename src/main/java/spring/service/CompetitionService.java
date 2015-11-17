package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.model.Competition;
import spring.model.CompetitionDAO;

@Service
public class CompetitionService {

	@Autowired private CompetitionDAO competitionDAO;
	
	@Transactional(readOnly=true)
	public List<Competition> findAll() {
		return this.competitionDAO.findAll();
	}
	
	@Transactional(readOnly=true)
	public List<String> home() {
		return this.competitionDAO.home();
	}	
}
