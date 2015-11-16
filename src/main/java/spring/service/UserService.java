package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.model.User;
import spring.model.UserDAO;

@Service
public class UserService {

	@Autowired private UserDAO userDAO;
	
	@Transactional(readOnly=true)
	public List<User> findAll() {
		return this.userDAO.findAll();
	}
	
	@Transactional(readOnly=true)
	public User findByLogin(String login, String pwd) {
		return this.userDAO.findByLogin(login, pwd);
	}
}
