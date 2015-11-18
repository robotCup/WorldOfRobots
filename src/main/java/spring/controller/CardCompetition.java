package spring.controller;

import java.sql.Timestamp;

public class CardCompetition {

	private String name, description, address, geolocation, duration;
	private int robot_max;
	private Timestamp date_start_1, date_start_2, date_start_3, date_start_4;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public int getRobot_max() {
		return robot_max;
	}
	public void setRobot_max(int robot_max) {
		this.robot_max = robot_max;
	}
	public Timestamp getDate_start_1() {
		return date_start_1;
	}
	public void setDate_start_1(Timestamp date_start_1) {
		this.date_start_1 = date_start_1;
	}
	public Timestamp getDate_start_2() {
		return date_start_2;
	}
	public void setDate_start_2(Timestamp date_start_2) {
		this.date_start_2 = date_start_2;
	}
	public Timestamp getDate_start_3() {
		return date_start_3;
	}
	public void setDate_start_3(Timestamp date_start_3) {
		this.date_start_3 = date_start_3;
	}
	public Timestamp getDate_start_4() {
		return date_start_4;
	}
	public void setDate_start_4(Timestamp date_start_4) {
		this.date_start_4 = date_start_4;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
}
