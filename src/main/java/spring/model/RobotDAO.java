package spring.model;

import java.io.Serializable;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class RobotDAO {
	@Autowired private SessionFactory sessionFactory;
	
	public List<Robot> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List robots = session.createQuery("from Robot").list();
		return robots;
	}

	public Robot findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Robot robot = (Robot) session
				.createQuery("from Robot r where r.id = :id")
		        .setParameter("id", id)
		        .uniqueResult();
		return robot;
	}

	public Robot createRobot(String name, String creation_date, String image) {
		Session session = sessionFactory.getCurrentSession();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Robot robot = new Robot();
		robot.setName(name);
		robot.setPath_picture(image);
		try {
			robot.setCreation_date(new Timestamp(formatter.parse(creation_date).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.persist(robot);		
		return robot;
	}

}
