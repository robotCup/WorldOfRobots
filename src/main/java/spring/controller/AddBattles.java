package spring.controller;

import java.sql.Timestamp;
import java.util.List;

public class AddBattles {
	private List<Integer> nbEquipes;
	private List<String> datesBattles;
	private int nbMatch;
	private int idCompetition;
	public List<String> getDatesBattles() {
		return datesBattles;
	}

	public void setDatesBattles(List<String> datesBattles) {
		this.datesBattles = datesBattles;
	}

	public List<Integer> getNbEquipes() {
		return nbEquipes;
	}

	public void setNbEquipes(List<Integer> nbEquipes) {
		this.nbEquipes = nbEquipes;
	}

	public int getNbMatch() {
		return nbMatch;
	}

	public void setNbMatch(int nbMatch) {
		this.nbMatch = nbMatch;
	}

	public int getIdCompetition() {
		return idCompetition;
	}

	public void setIdCompetition(int idCompetition) {
		this.idCompetition = idCompetition;
	}


}
