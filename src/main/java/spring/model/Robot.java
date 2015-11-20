package spring.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="robot")
public class Robot {

	@Id @GeneratedValue
	private int id;
	private String name;
	private Timestamp creation_date;
	private String strong_point;
	private String path_picture;
	
	@Transient
	private List<Technology> technologies;
	
	public String getPath_picture() {
		return path_picture;
	}
	public void setPath_picture(String path_picture) {
		this.path_picture = path_picture;
	}
	public String getStrong_point() {
		return strong_point;
	}
	public void setStrong_point(String strong_point) {
		this.strong_point = strong_point;
	}
	public Timestamp getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Timestamp creation_date) {
		this.creation_date = creation_date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Technology> getTechnologies() {
		return technologies;
	}
	public void setTechnologies(List<Technology> technologies) {
		this.technologies = technologies;
	}
}
