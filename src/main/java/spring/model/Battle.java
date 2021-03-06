package spring.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="battle")
public class Battle {

	@Id @GeneratedValue
	private int id;

	private int id_winner;
	private int id_competition;
	private Timestamp date;
	private int nb_robot_max;
	@Transient
	private List <Robot> robots;

	
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
	public List <Robot> getRobots() {
		return robots;
	}
	public void setRobots(List <Robot> robots) {
		this.robots = robots;
	}
	
	public int getId(){
		return this.id;
	}
	public int getNb_robot_max() {
		return nb_robot_max;
	}
	public void setNb_robot_max(int nb_robot_max) {
		this.nb_robot_max = nb_robot_max;
	}


}
