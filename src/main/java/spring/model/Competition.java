package spring.model;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="competition")
public class Competition {
	
	@Id @GeneratedValue
	private long id;
	private String name;
	private Integer id_place;
	private String description;
	private Timestamp start_date;
	private Timestamp end_date;
	private int robot_max;
	@Transient
	private List<Battle> battles;
	@Transient
	private Place place;
	
	public long getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}

	public int getRobot_max() {
		return robot_max;
	}
	public Timestamp getStart_date() {
		return start_date;
	}
	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}
	public Timestamp getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}

	public int getId_place() {
		return id_place;
	}
	public void setId_place(int id_place) {
		this.id_place = id_place;
	}
	public List<Battle> getBattles() {
		return battles;
	}
	public void setBattles(List<Battle> battles) {
		this.battles = battles;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
}
