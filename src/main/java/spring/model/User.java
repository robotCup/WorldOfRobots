package spring.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id @GeneratedValue
	private int id;

	private String login;
	private String pwd;
	private String email;
	private boolean leader;
	private int id_robot;
	
	//@OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
	public String getLogin() {
		return login;
	}
	public void setLogin(String user) {
		this.login = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	public boolean isLeader() {
		return leader;
	}
	public void setLeader(boolean leader) {
		this.leader = leader;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId_robot() {
		return id_robot;
	}
	public void setId_robot(int id_robot) {
		this.id_robot = id_robot;
	}
}

