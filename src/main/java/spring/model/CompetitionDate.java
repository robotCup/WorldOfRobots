package spring.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="competition_date")
public class CompetitionDate {

	@Id @GeneratedValue
	private int id;
	private int id_competition;
	private Timestamp date_1, date_2, date_3, date_4;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_competition() {
		return id_competition;
	}
	public void setId_competition(int id_competition) {
		this.id_competition = id_competition;
	}
	public Timestamp getDate_1() {
		return date_1;
	}
	public void setDate_1(Timestamp date_1) {
		this.date_1 = date_1;
	}
	public Timestamp getDate_2() {
		return date_2;
	}
	public void setDate_2(Timestamp date_2) {
		this.date_2 = date_2;
	}
	public Timestamp getDate_3() {
		return date_3;
	}
	public void setDate_3(Timestamp date_3) {
		this.date_3 = date_3;
	}
	public Timestamp getDate_4() {
		return date_4;
	}
	public void setDate_4(Timestamp date_4) {
		this.date_4 = date_4;
	}	
}



