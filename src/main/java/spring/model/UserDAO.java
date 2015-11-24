package spring.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})

public class UserDAO {

	
	@Autowired private SessionFactory sessionFactory;
	
	/**
	 * @Transactional annotation below will trigger Spring Hibernate transaction manager to automatically create
	 * a hibernate session. See src/main/webapp/WEB-INF/servlet-context.xml	 */
	
	
	public List<User> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List Users = session.createQuery("from User").list();
		return Users;
	}	

	public User findByLogin(String login, String pwd) {
		Session session = sessionFactory.getCurrentSession();
		User user=(User) session
        .createQuery("from User u where u.login = :login and u.pwd = :pwd")
        .setParameter("login", login)
        .setParameter("pwd", pwd)
        .uniqueResult();
		return user;
	}	
	
	public User createUser(String login, String pwd, String email) throws MySQLIntegrityConstraintViolationException,ConstraintViolationException{
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		user.setEmail(email);
		user.setLogin(login);
		user.setPwd(pwd);
		user.setLast_date_pwd(new Date());
		session.persist(user);
		return user;
	}

	public void createRobot(int id_user, int id_robot) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		User user=(User) session
		        .createQuery("from User u where u.id = :id")
		        .setParameter("id", id_user)
		        .uniqueResult();
		
		user.setId_robot(id_robot);
		user.setLeader(true);
		session.update(user);
		tx.commit();
	}
	public User findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user=(User) session
        .createQuery("from User u where u.id = :id")
        .setParameter("id", id)
        .uniqueResult();
		return user;
	}

	public void updateUser(int id, String login, String pwd, String email) {
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		user.setEmail(email);
		user.setId(id);
		user.setLogin(login);
		user.setPwd(pwd);
		user.setLeader(true);
		session.update(user);
	}

	public void checkDatePassword(User user) {
		// TODO Auto-generated method stub
		Date d= new Date();
		if (user.getLast_date_pwd().before(d)){
			user.setLast_date_pwd(null);
			Session session = sessionFactory.getCurrentSession();
			session.update(user);
		}
	}	
}
