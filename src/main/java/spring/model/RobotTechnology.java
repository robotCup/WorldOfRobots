package spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="robot_technology")
public class RobotTechnology {

	@Id @GeneratedValue
	private int id;
	private int id_robot, id_technology;
	
	public int getId_technology() {
		return id_technology;
	}
	public void setId_technology(int id_technology) {
		this.id_technology = id_technology;
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