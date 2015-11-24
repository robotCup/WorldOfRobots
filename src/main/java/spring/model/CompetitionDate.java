package spring.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="competition_date")
public class CompetitionDate {

	@Id @GeneratedValue
	private int id;
	private int id_competition, vote;
	private Timestamp date;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_competition() {
		return id_competition;
	}
	public void setId_competition(int id_competition) {
		this.id_competition = id_competition;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
}



