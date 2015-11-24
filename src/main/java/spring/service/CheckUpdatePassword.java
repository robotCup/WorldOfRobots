package spring.service;

import java.util.TimerTask;

public class CheckUpdatePassword extends TimerTask {
	
	private int id_user;
	private UserService userService;
	
	public CheckUpdatePassword(int id, UserService userService){
		this.id_user=id;
		this.userService=userService;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.userService.checkDatePassword(this.id_user);
	}

}
