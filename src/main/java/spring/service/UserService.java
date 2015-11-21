package spring.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import spring.model.User;
import spring.model.UserDAO;

@Service
public class UserService {

	@Autowired private UserDAO userDAO;
	
	@Transactional(readOnly=true)
	public List<User> findAll() {
		return this.userDAO.findAll();
	}
	
	@Transactional(readOnly = true)
	public User findByLogin(String login, String pwd) {
		return this.userDAO.findByLogin(login, pwd);
	}
	
	@Transactional
	public void createUser(String login, String pwd, String email)throws MySQLIntegrityConstraintViolationException, ConstraintViolationException {
		// TODO Auto-generated method stub
		 this.userDAO.createUser(login, pwd, email);
	}

	public void createRobot(int id_user, int id_robot) {
		// TODO Auto-generated method stub
		this.userDAO.createRobot(id_user, id_robot);
		
	}
}
