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
	private int id;
	private String name, description, address, geolocation;
	private Timestamp start_date, end_date;
	private int robot_max, id_user;
	private boolean close_participate, close_vote;

	@Transient
	private List<Battle> battles;
	
	public int getId(){
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
	public void setRobot_max(int robot_max ) {
		this.robot_max = robot_max;
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

	public List<Battle> getBattles() {
		return battles;
	}
	public void setBattles(List<Battle> battles) {
		this.battles = battles;
	}
	public String getGeolocation() {
		return geolocation;
	}
	public void setGeolocation(String geolocation) {
		this.geolocation = geolocation;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public boolean getClose_participate() {
		return close_participate;
	}
	public void setClose_participate(boolean close_participate) {
		this.close_participate = close_participate;
	}
	public boolean getClose_vote() {
		return close_vote;
	}
	public void setClose_vote(boolean close_vote) {
		this.close_vote = close_vote;
	}
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
}
