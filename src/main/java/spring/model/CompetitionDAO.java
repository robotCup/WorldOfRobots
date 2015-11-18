package spring.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

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
	
	public void createCompetition(int id_user, String name, String desc, String start_date, int robot_max, String address, String geolocation, int duration, String start_date_1, String start_date_2, String start_date_3, String start_date_4) 
			throws MySQLIntegrityConstraintViolationException,ConstraintViolationException{
		
		Session session = sessionFactory.getCurrentSession();
		Competition competition = new Competition();
		CompetitionDate competition_date = new CompetitionDate();
		
		competition.setName(name);
		competition.setDescription(desc);
		competition.setRobot_max(robot_max);
		competition.setGeolocation(geolocation);
		competition.setAddress(address);
		competition.setId_user(id_user);
		
		session.persist(competition);		

		try {
			DateFormat formatter;
			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			//date debut
			if(start_date != ""){
				Date date_s = (Date) formatter.parse(start_date);
				Timestamp format_start_date = new Timestamp(date_s.getTime());
				
				//date fin 
				Timestamp format_end_date = new Timestamp(date_s.getTime());
				long number_days = duration * 24 * 60 * 60 * 1000;
				// to add to the timestamp
				format_end_date.setTime(format_end_date.getTime() + number_days);
				
				competition.setStart_date(format_start_date);
				competition.setEnd_date(format_end_date);

				session.persist(competition);
			}
			else if(start_date_1 != "" && (start_date_2 != "" || start_date_3 !="" || start_date_4 !="")){
				//date 1 
				Date date_s = (Date) formatter.parse(start_date_1);
				Timestamp format_start_date = new Timestamp(date_s.getTime());
				competition_date.setDate_1(format_start_date);
				
				//date 2
				if(start_date_2 != ""){
					date_s = (Date) formatter.parse(start_date_2);
					format_start_date = new Timestamp(date_s.getTime());
					competition_date.setDate_2(format_start_date);
				}				
				
				//date 3
				if(start_date_3 != ""){
					date_s = (Date) formatter.parse(start_date_3);
					format_start_date = new Timestamp(date_s.getTime());
					competition_date.setDate_3(format_start_date);
				}
				
				//date 4
				if(start_date_4 != ""){
					date_s = (Date) formatter.parse(start_date_4);
					format_start_date = new Timestamp(date_s.getTime());
					competition_date.setDate_4(format_start_date);
				}
				
				competition_date.setId_competition(competition.getId());
				session.persist(competition_date);				
			}
		} 
		catch (ParseException e) {
			System.out.println("Exception :" + e);
		}		
	}	
}
