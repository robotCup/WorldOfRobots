package spring.controller;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import spring.model.Technology;

public class AddRobot {

	private String name, creation_date, strong_point;
	private MultipartFile image;
	private List<Integer> technologies;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	public List<Integer> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<Integer> technologies) {
		this.technologies = technologies;
	}

	public String getStrong_point() {
		return strong_point;
	}

	public void setStrong_point(String strong_point) {
		this.strong_point = strong_point;
	}
}
