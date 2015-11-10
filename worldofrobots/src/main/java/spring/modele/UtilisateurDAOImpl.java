/*package spring.modele;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

public class UtilisateurDAOImpl implements UtilisateurDAO{

	
	private static final Logger logger = LoggerFactory.getLogger(UtilisateurDAOImpl.class);
	 
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
	public Utilisateur getUtilisateurById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
        Utilisateur p = (Utilisateur) session.load(Utilisateur.class, new Integer(id));
        logger.info("Person loaded successfully, Person details="+p);
        return p;
	}

}*/
