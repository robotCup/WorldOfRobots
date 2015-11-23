package spring.controller;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import spring.model.Technology;

public class AddRobot {

	private String name, creation_date, strong_point;
	private MultipartFile image;
	private List<String> technologies;
	
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

	public String getStrong_point() {
		return strong_point;
	}

	public void setStrong_point(String strong_point) {
		this.strong_point = strong_point;
	}

	public List<String> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<String> technologies) {
		this.technologies = technologies;
	}
}
