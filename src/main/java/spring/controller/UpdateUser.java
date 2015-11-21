package spring.controller;

public class UpdateUser {

	private String login, pwd, email,pwdOld;
	private int id;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPwdOld() {
		return pwdOld;
	}
	public void setPwdOld(String pwdOld) {
		this.pwdOld = pwdOld;
	}
}
