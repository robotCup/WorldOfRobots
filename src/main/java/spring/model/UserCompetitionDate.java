package spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_competition_date")
public class UserCompetitionDate {

	@Id @GeneratedValue
	private int id;
	
	private int id_user, id_competition;

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_competition() {
		return id_competition;
	}

	public void setId_competition(int id_competition) {
		this.id_competition = id_competition;
	}

}