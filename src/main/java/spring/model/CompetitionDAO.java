package spring.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})

public class CompetitionDAO {
	
	@Autowired private SessionFactory sessionFactory;
	
	/**
	 * @Transactional annotation below will trigger Spring Hibernate transaction manager to automatically create
	 * a hibernate session. See src/main/webapp/WEB-INF/servlet-context.xml	 */
	@Transactional
	public List<Competition> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List competitions = session.createQuery("from Competition").list();
		
		for( int i=0; i<competitions.size();i++) {
		    ((Competition) competitions.get(i)).setPlace((Place) session
		            .createQuery("from Place p where p.id = :id")
		            .setParameter("id", ((Competition) competitions.get(i)).getId_place())
		            .uniqueResult());
		}
		
		return competitions;
	}	
}
