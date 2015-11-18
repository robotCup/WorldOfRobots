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
		return competitions;

	}
	
	public Competition findByID(int id) {
		Session session = sessionFactory.getCurrentSession();
		Competition competition = (Competition) session.createQuery("from Competition c where c.id = :id")
        .setParameter("id", id)
        .uniqueResult();
		List<Battle> battles =session.createQuery("from Battle b where b.id_competition = :id")
		        .setParameter("id", competition.getId())
		        .list();
		for (int i =0; i<battles.size();i++){
			 battles.get(i).setRobots(session.createQuery("Select r from Robot as r, RobotBattle as rb where rb.id_battle = :id and rb.id_robot = r.id")
					 .setParameter("id", battles.get(i).getId())
				        .list());
		}
		competition.setBattles(battles);
		return competition;


	}
	
	

	

}
