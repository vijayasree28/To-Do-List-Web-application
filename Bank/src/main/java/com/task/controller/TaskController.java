package com.task.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.doa.TaskDAO;
import com.task.model.Task;

@RestController
public class TaskController {
	@Autowired
	Task task;
	
	@Autowired
	TaskDAO taskDAO;
	
	@RequestMapping(value="/task",method=RequestMethod.GET)
	public ResponseEntity<List<Task>> listAllTask(){
		List<Task> tasks=taskDAO.list();
		if(tasks.isEmpty()){
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Task>>(HttpStatus.OK);
	}

	@RequestMapping(value="/task/",method=RequestMethod.POST)
	public ResponseEntity<Task> createTask(@RequestBody Task task,HttpSession session){
		Integer loogedInUserID=(Integer) session.getAttribute("loggedInUserID");
		task.setUserid(loogedInUserID);
		task.setStatus("pending");
		if(taskDAO.save(task)==true){
			task.setErrorCode("200");
			task.setErrorMsge("task posted");
		}
		
		
		return new ResponseEntity<Task>(task,HttpStatus.OK);
			}
	
	@RequestMapping(value="/taskUpdate/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Task> updateTask(@PathVariable("id") int tasksid,@RequestBody Task task,HttpSession session){
		
		Integer loogedInUserID=(Integer) session.getAttribute("loggedInUserID");
		task.setUserid(loogedInUserID);
		if(taskDAO.update(task)==true){
			task.setErrorCode("200");
			task.setErrorMsge("task posted");
		}
		
		
		return new ResponseEntity<Task>(task,HttpStatus.OK);
			}

	@RequestMapping(value="/taskDelete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Task> deleteTask(@PathVariable("id") int taskid){
		
		
		Task task=taskDAO.get(taskid);
		
		if(task==null){
			
			task=new Task();
			task.setErrorMsge("task does not exists with id:" + taskid);
			return new ResponseEntity<Task> (task,HttpStatus.NOT_FOUND);	
		}else
		taskDAO.delete(task);
		
		return new ResponseEntity<Task> (task,HttpStatus.OK);		
	}
	
	@RequestMapping(value="/task/{id}",method=RequestMethod.GET)
	public ResponseEntity<Task> getTask(@PathVariable("id") int taskid){
		
		Task task=taskDAO.get(taskid);
		if(task==null){
			
			task=new Task();
			
			return new ResponseEntity<Task> (task,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Task> (task,HttpStatus.OK);
	}

	@RequestMapping(value="/taskByUser/{uid}",method=RequestMethod.GET)
	public ResponseEntity<List<Task>> listTaskByUser(@PathVariable("uid") int userid){
		@SuppressWarnings("unchecked")
		List<Task> task=(List<Task>) taskDAO.getByUser(userid);
		if(task.isEmpty()){
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>>(task,HttpStatus.OK);
	}

	
}
