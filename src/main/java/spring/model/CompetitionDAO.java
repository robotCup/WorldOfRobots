package spring.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class CompetitionDAO {

	@Autowired private SessionFactory sessionFactory;

	/**
	 * @Transactional annotation below will trigger Spring Hibernate transaction manager to automatically create
	 * a hibernate session. See src/main/webapp/WEB-INF/servlet-context.xml	 */

	public List<Competition> findAll() {
		Session session = sessionFactory.getCurrentSession();

		List competitions = session.createQuery("from Competition c where c.start_date > :now order by c.start_date").list();
		return competitions;
	}

	public List<Competition> findAllFuture() {
		Session session = sessionFactory.getCurrentSession();
		Date now = new Date();
		Timestamp now_timestamp = new Timestamp(now.getTime());

		List competitions = session.createQuery("from Competition c where c.start_date = null or c.start_date > :now order by c.start_date")
				.setParameter("now", now_timestamp)
				.list();
		return competitions;
	}

	public List<Competition> findAllPast() {
		Session session = sessionFactory.getCurrentSession();
		Date now = new Date();
		Timestamp now_timestamp = new Timestamp(now.getTime());

		List competitions = session.createQuery("from Competition c where c.start_date < :now order by c.start_date")
				.setParameter("now", now_timestamp)
				.list();
		return competitions;
	}

	public List<Competition> findAllMyCompetitions(int id_user) {
		Session session = sessionFactory.getCurrentSession();

		List competitions = session.createQuery("from Competition c where c.id_user = :id")
				.setParameter("id", id_user)
				.list();
		return competitions;

	}

	public Competition findByID(int id) {
		Session session = sessionFactory.getCurrentSession();

		Competition competition = (Competition) session.createQuery("from Competition c where c.id = :id")
				.setParameter("id", id)
				.uniqueResult();

		List<Battle> battles = session.createQuery("from Battle b where b.id_competition = :id")
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

	public Competition createCompetition(int id_user, int id_robot, String name, String desc, String start_date, int robot_max, String address, String geolocation, int duration, String start_date_1, String start_date_2, String start_date_3, String start_date_4) 
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
		competition.setDuration(duration);
		
		session.persist(competition);

		RobotCompetition robot_competition = new RobotCompetition();
		robot_competition.setId_competition(competition.getId());
		robot_competition.setId_robot(id_robot);
		
		session.persist(robot_competition);
		

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
				format_end_date.setTime(format_end_date.getTime() + number_days);

				competition.setStart_date(format_start_date);
				competition.setEnd_date(format_end_date);

				session.persist(competition);
			}
			else if(start_date_1 != "" && (start_date_2 != "" || start_date_3 !="" || start_date_4 !="")){
				//date 1 
				Date date_s = (Date) formatter.parse(start_date_1);
				Timestamp format_start_date = new Timestamp(date_s.getTime());
				competition_date.setDate(format_start_date);

				competition_date.setId_competition(competition.getId());
				session.persist(competition_date);	

				//date 2
				if(start_date_2 != ""){
					competition_date =new CompetitionDate();
					competition_date.setId_competition(competition.getId());
					date_s = (Date) formatter.parse(start_date_2);
					format_start_date = new Timestamp(date_s.getTime());
					competition_date.setDate(format_start_date);

					competition_date.setId_competition(competition.getId());
					session.persist(competition_date);	
				}				

				//date 3
				if(start_date_3 != ""){
					competition_date =new CompetitionDate();
					competition_date.setId_competition(competition.getId());
					date_s = (Date) formatter.parse(start_date_3);
					format_start_date = new Timestamp(date_s.getTime());
					competition_date.setDate(format_start_date);

					competition_date.setId_competition(competition.getId());
					session.persist(competition_date);	
				}

				//date 4
				if(start_date_4 != ""){
					competition_date =new CompetitionDate();
					competition_date.setId_competition(competition.getId());
					date_s = (Date) formatter.parse(start_date_4);
					format_start_date = new Timestamp(date_s.getTime());
					competition_date.setDate(format_start_date);

					competition_date.setId_competition(competition.getId());
					session.persist(competition_date);	
				}							
			}
		} 
		catch (ParseException e) {
			System.out.println("Exception :" + e);
		}	
		return competition;
	}

	public long getNbFighters(int id_competition) {
		Session session = sessionFactory.getCurrentSession();		

		long count = (Long) session.createQuery("select count (*) from RobotCompetition rc where rc.id_competition = :id")
				.setParameter("id", id_competition)
				.uniqueResult();

		return count;
	}

	public Boolean isParticiped(int id_user, int id_competition) {
		Session session = sessionFactory.getCurrentSession();	

		int id_robot = (Integer) session.createQuery("select u.id_robot from User u where u.id = :id_user")
				.setParameter("id_user", id_user)				
				.uniqueResult();
		
		Long count = (Long) session.createQuery("select count (rc.id) from RobotCompetition rc where rc.id_competition = :id_competition and rc.id_robot = :id_robot")
				.setParameter("id_competition", id_competition)
				.setParameter("id_robot", id_robot)
				.uniqueResult();		
		
		if(count != 0){
			return true;
		}
		return false;
	}

	public void toParticipate(int id_robot, int id_competition) {
		Session session = sessionFactory.getCurrentSession();	

		RobotCompetition robot_competition = new RobotCompetition();
		robot_competition.setId_competition(id_competition);
		robot_competition.setId_robot(id_robot);
		session.persist(robot_competition);			
	}

	public void closeParticipate(Competition competition) {
		Session session = sessionFactory.getCurrentSession();	
		competition.setClose_participate(true);		
		session.update(competition);

	}

	public void createBattles(int idCompetition, int nbMatch, List<String> datesBattles, List<Integer> nbEquipes, List<Robot> robots) {
		// TODO Auto-generated method stub
		DateFormat formatter;
		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Session session = sessionFactory.getCurrentSession();
		for (int i=0; i<nbMatch; i++){
			Battle battle = new Battle();
			battle.setId_competition(idCompetition);
			battle.setNb_robot_max(nbEquipes.get(i));
			Date date_s;
			try {
				System.out.println("ok");
				System.out.println(datesBattles.get(i));
				date_s = (Date) formatter.parse(datesBattles.get(i));
				Timestamp format_start_date = new Timestamp(date_s.getTime());
				battle.setDate(format_start_date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int j=0; j<nbEquipes.get(i);j++){
				int sizeRobots = (robots.size());
			int random = (int)(Math.random() * (sizeRobots-0)) + 0;
			RobotBattle robotBattle = new RobotBattle();
			robotBattle.setId_robot(robots.remove(random).getId());
			session.persist(battle);
			robotBattle.setId_battle(battle.getId());
			session.persist(robotBattle);
			}
		}
	}

	public List<RobotCompetition> findRobotCompetitionById(int id) {
		Session session = sessionFactory.getCurrentSession();
		List robotCompetitions = session
				.createQuery("from RobotCompetition r where r.id_competition = :id")
				.setParameter("id", id)
				.list();
		return robotCompetitions;
	}	

	public List<CompetitionDate> findAllDates(int id) {
		Session session = sessionFactory.getCurrentSession();	

		List<CompetitionDate> dates = session.createQuery("from CompetitionDate cd where cd.id_competition = :id")
				.setParameter("id", id)
				.list();

		return dates;
	}

	public CompetitionDate findCompetitionDate(int id){
		Session session = sessionFactory.getCurrentSession();		

		CompetitionDate competitionDate = (CompetitionDate) session.createQuery("from CompetitionDate cd where cd.id = :id")
				.setParameter("id", id)
				.uniqueResult();

		return competitionDate;
	}
	
	
	public void vote(CompetitionDate competitonDate, int id_user) {
		Session session = sessionFactory.getCurrentSession();

		competitonDate.setVote(competitonDate.getVote()+1);	
		session.update(competitonDate);
		
		UserCompetitionDate user_competition_date = new UserCompetitionDate();
		user_competition_date.setId_competition(competitonDate.getId_competition());
		user_competition_date.setId_user(id_user);
		
		session.persist(user_competition_date);
	}

	public UserCompetitionDate findVote(int id_competition, int id_user) {
		Session session = sessionFactory.getCurrentSession();		

		UserCompetitionDate user_competition_date = (UserCompetitionDate) session.createQuery("from UserCompetitionDate ucd where ucd.id_competition = :id_competition and ucd.id_user = :id_user")
				.setParameter("id_competition", id_competition)
				.setParameter("id_user", id_user)
				.uniqueResult();

		return user_competition_date;
	}

	public CompetitionDate findDateWin(int id){
		Session session = sessionFactory.getCurrentSession();		

		Integer max_votes = (Integer) session.createQuery("select MAX(vote) from CompetitionDate cd where cd.id_competition = :id order by cd.id")
				.setParameter("id", id)
				.uniqueResult();
		
		List<CompetitionDate> competitionDate = session.createQuery("from CompetitionDate cd where cd.id_competition = :id and vote = :max_votes")
				.setParameter("max_votes", max_votes)
				.setParameter("id", id)
				.list();

		return competitionDate.get(0);
	}
	
	public void closeVote(Competition competition) {
		Session session = sessionFactory.getCurrentSession();	
		
		CompetitionDate competition_date = this.findDateWin(competition.getId());
		competition.setStart_date(competition_date.getDate());
		
		Timestamp format_end_date = new Timestamp(competition_date.getDate().getTime());
		long number_days = competition.getDuration() * 24 * 60 * 60 * 1000;
		format_end_date.setTime(format_end_date.getTime() + number_days);		
		
		competition.setEnd_date(format_end_date);
		competition.setClose_vote(true);
		session.update(competition);		
	}

	public void winnerBattle(Battle battle, int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		battle.setId_winner(id);
		session.update(battle);
	}	
	public Battle getBattleById(int id){
		Session session = sessionFactory.getCurrentSession();
		Battle battle = (Battle) session.createQuery("from Battle b where b.id = :id")
				.setParameter("id", id)
				.uniqueResult();
		return battle;
	}


	public void winnerCompetition(Competition competition, int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		System.out.println("test" + id);
		competition.setId_winner(id);
		session.update(competition);
	}
	
	public List<User> findUserByCompetition(int id_competition) {
		Session session = sessionFactory.getCurrentSession();
		
		List<User> users = new ArrayList<User>();
		
		List<Integer> id_users = session.createQuery("select c.id_user from Competition c where c.id = :id")
				.setParameter("id", id_competition)
				.list();
		
		for(Integer id_user : id_users){
			users.add(
					(User) session.createQuery("from User u where u.id = :id")
					.setParameter("id", id_user)
					.uniqueResult());
		}
		return users;
	}

	public void deleteCompetition(Competition competition) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("delete RobotCompetition rb where rb.id_competition = :id")
		.setParameter("id", competition.getId())
		.executeUpdate();
		session.createQuery("delete CompetitionDate cd where cd.id_competition = :id")
		.setParameter("id", competition.getId())
		.executeUpdate();
		session.delete(competition);
	}

	public Timestamp findMinCompetitionDateByIdCompetition(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Timestamp date=(Timestamp) session.createQuery("select MIN(cd.date) from CompetitionDate cd where cd.id_competition = :id order by cd.id")
		.setParameter("id", id)
		.uniqueResult();
		return date;
		
	}
}	


