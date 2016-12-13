package com.task.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Table(name="TASKS")
@Component
public class Task  extends ErrorMessage{
	
	@Id
	//@GeneratedValue(generator="InvSeq") //InventorySequence
    //@SequenceGenerator(name="InvSeq",sequenceName="TASKS_SEQ", allocationSize=1) 
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int taskid;
	@NotEmpty(message="Please fill the title")
	private String title;
	@NotEmpty(message="Please fill the description")
	private String description;
	private int userid;
	private String status;
	private String taskdate;
	public int getTaskid() {
		return taskid;
	}
	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTaskdate() {
		return taskdate;
	}
	public void setTaskdate(String taskdate) {
		this.taskdate = taskdate;
	}
	
	
	
	

}
