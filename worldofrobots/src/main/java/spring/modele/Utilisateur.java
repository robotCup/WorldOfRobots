package spring.modele;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="utilisateur")
public class Utilisateur {
	@Id @GeneratedValue
	private long id;
	
	private String user;
	private String pwd;
	@OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public long getId(){
		return this.id;
	}
	public void setId(long id){
		this.id=id;
	}
	}

