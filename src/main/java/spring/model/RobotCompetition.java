package spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="robot_competition")
public class RobotCompetition {

	@Id @GeneratedValue
	private int id;
	private int id_robot;
	private int id_competition;
	
	
	public int getId_robot() {
		return id_robot;
	}
	public void setId_robot(int id_robot) {
		this.id_robot = id_robot;
	}
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
}