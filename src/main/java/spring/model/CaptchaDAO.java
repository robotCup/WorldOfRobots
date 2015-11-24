package spring.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class CaptchaDAO {

	
	@Autowired private SessionFactory sessionFactory;
	public Captcha captcha(int random) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Captcha captcha =(Captcha) session
        .createQuery("from Captcha c where c.id = :id")
        .setParameter("id", random)
        .uniqueResult();
		return captcha;
	}

}
