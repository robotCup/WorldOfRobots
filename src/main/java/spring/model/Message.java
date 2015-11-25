package spring.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="message")
public class Message {

	@Id @GeneratedValue
	private int id;

	private int id_user;
	private String message;
	private Timestamp date;	

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}	
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
