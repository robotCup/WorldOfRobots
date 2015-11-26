package spring.service;

import java.util.List;
import java.util.Timer;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import spring.model.Captcha;
import spring.model.CaptchaDAO;
import spring.model.Message;
import spring.model.User;
import spring.model.UserDAO;

@Service
public class UserService {

	@Autowired private UserDAO userDAO;
	@Autowired private CaptchaDAO captchaDAO;
	
	@Transactional(readOnly=true)
	public List<User> findAll() {
		return this.userDAO.findAll();
	}
	
	@Transactional(readOnly = true)
	public User findByLogin(String login, String pwd) {
		return this.userDAO.findByLogin(login, pwd);
	}
	
	@Transactional
	public User createUser(String login, String pwd, String email)throws MySQLIntegrityConstraintViolationException, ConstraintViolationException {
		// TODO Auto-generated method stub
		 return this.userDAO.createUser(login, pwd, email);
	}
	
	@Transactional
	public void createRobot(int id_user, int id_robot) {
		// TODO Auto-generated method stub
		this.userDAO.createRobot(id_user, id_robot);
	}
	
	@Transactional(readOnly = true)
	public User findById(int id) {
		// TODO Auto-generated method stub
		return this.userDAO.findById(id);
	}
	
	@Transactional
	public void updateUser(int id, String login, String pwd, String email) {
		this.userDAO.updateUser(id, login, pwd, email);		
	}

	@Transactional
	public void checkDatePassword(int id_user) {
		// TODO Auto-generated method stub
		User user =this.userDAO.findById(id_user);
		this.userDAO.checkDatePassword(user);
	}
	@Transactional
	public Captcha captcha(int random) {
		// TODO Auto-generated method stub
		return this.captchaDAO.captcha(random);
	}
	
	@Transactional
	public List<Message> findAllMessageByUser(int id_user) {
		// TODO Auto-generated method stub
		return this.userDAO.findAllMessageByUser(id_user);
	}
	
	@Transactional
	public void createMessage(int id, String message) {
		// TODO Auto-generated method stub
		this.userDAO.createMessage(id, message);
	}
	@Transactional
	public void deleteNews(int id) {
		// TODO Auto-generated method stub
		this.userDAO.deleteNews(id);
	}
}
