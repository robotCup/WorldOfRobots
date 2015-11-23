package spring.controller;

public class Register {


	private String login, pwd, email,captcha,pwd_confirm;
	private int idCaptcha;

	
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

	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public int getIdCaptcha() {
		return idCaptcha;
	}
	public void setIdCaptcha(int idCaptcha) {
		this.idCaptcha = idCaptcha;
	}
	public String getPwd_confirm() {
		return pwd_confirm;
	}
	public void setPwd_confirm(String pwd_confirm) {
		this.pwd_confirm = pwd_confirm;

	}
}
