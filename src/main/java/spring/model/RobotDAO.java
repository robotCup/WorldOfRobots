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

		List<Technology> technologies = session
				.createQuery("Select t from RobotTechnology rt, Technology t where rt.id_robot = :id and rt.id_technology = t.id")
				.setParameter("id", id)
				.list();

		robot.setTechnologies(technologies);

		return robot;
	}

	public Robot createRobot(List<String> technologies, String strong_point, String name, String creation_date, String image) {
		Session session = sessionFactory.getCurrentSession();

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Robot robot = new Robot();		

		robot.setName(name);
		robot.setStrong_point(strong_point);
		robot.setPath_picture(image);

		try {
			robot.setCreation_date(new Timestamp(formatter.parse(creation_date).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.persist(robot);	

		if(!technologies.isEmpty()){
			
			for(String name_technology : technologies){
				
				Technology technology = this.findTechnologyByName(name_technology);
				if(technology == null){
					technology = new Technology();
					technology.setName(name_technology);
					session.persist(technology);
				}
				RobotTechnology robot_technology = new RobotTechnology();

				robot_technology.setId_robot(robot.getId());
				robot_technology.setId_technology(technology.getId());
				session.persist(robot_technology);	
			}
		}
		
		return robot;
	}

	public Technology findTechnologyById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Technology technology = (Technology) session
				.createQuery("from Technology t where t.id = :id")
				.setParameter("id", id)
				.uniqueResult();
		
		return technology;
	}
	
	public Technology findTechnologyByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		
		name = name.trim();
		Technology technology = (Technology) session
				.createQuery("from Technology t where t.name = :name")
				.setParameter("name", name)
				.uniqueResult();
		
		return technology;
	}

	public List<Technology> findAllTechnologies() {
		Session session = sessionFactory.getCurrentSession();
		List technologies = session.createQuery("from Technology").list();
		return technologies;
	}
}
