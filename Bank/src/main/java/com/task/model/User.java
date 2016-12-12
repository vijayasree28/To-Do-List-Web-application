package com.task.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Table(name="USERDATA")
@Component
public class User extends ErrorMessage {
	
	@Id
	@GeneratedValue(generator="InvSeq") //InventorySequence
    @SequenceGenerator(name="InvSeq",sequenceName="USERDATA_SEQ", allocationSize=1) //size can be upto 5
	private int userid;

	@NotEmpty
	private String username;
	@NotEmpty(message="Password is must")
	private String password;
	@Transient
	private String confirmpassword;	
	private String email;
    @Size(min=10,max=10)
	private String contact;
	private String city;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
