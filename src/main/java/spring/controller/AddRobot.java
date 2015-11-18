package spring.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class AddRobot {

	private String name;
	private String creation_date;
	private MultipartFile image;
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

}
