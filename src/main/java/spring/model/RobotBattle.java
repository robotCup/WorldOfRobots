package spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="robot_battle")
public class RobotBattle {

	@Id @GeneratedValue
	private int id;
	private int id_robot;
	private int id_battle;
	
	public int getId_battle() {
		return id_battle;
	}
	public void setId_battle(int id_battle) {
		this.id_battle = id_battle;
	}
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
	
}



