package spring.model;

import java.sql.Timestamp;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table(name="battle")
public class Battle {

	@Id @GeneratedValue
	private int id;

	private Integer id_winner;
	private int id_competition;
	private Timestamp date;


	
//	@OneToMany(mappedBy = "Robot", fetch = FetchType.LAZY)
	//private List<Robot> Robot;


	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getId_competition() {
		return id_competition;
	}
	public void setId_competition(int id_competition) {
		this.id_competition = id_competition;
	}
	public int getId_winner() {
		return id_winner;
	}
	public void setId_winner(int id_winner) {
		this.id_winner = id_winner;
	}


}
