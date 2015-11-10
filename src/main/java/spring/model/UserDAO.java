package spring.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})

public class UserDAO {

	
	@Autowired private SessionFactory sessionFactory;
	
	/**
	 * @Transactional annotation below will trigger Spring Hibernate transaction manager to automatically create
	 * a hibernate session. See src/main/webapp/WEB-INF/servlet-context.xml	 */
	@Transactional
	public List<User> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List Users = session.createQuery("from User").list();
		return Users;
	}
	
	@Transactional
	public List <User> findByLogin(String login, String pwd) {
		Session session = sessionFactory.getCurrentSession();
		List Users = session.createQuery("from User where login = '"+login+ "' and pwd = '"+ pwd + "'").list();
		return Users;
	}
}
