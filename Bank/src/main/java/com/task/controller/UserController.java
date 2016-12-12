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

import com.task.doa.UserDAO;
import com.task.model.User;

@RestController
public class UserController {
	
	
	@Autowired
	UserDAO userDAO;	
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUser(){
		List<User> user=userDAO.list();
		if(user.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(user,HttpStatus.OK);
	}

	@RequestMapping(value="/user/",method=RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		if(userDAO.get(user.getUserid())==null){
			userDAO.save(user);			
		}
		
		user.setErrorMsge("user already exists with id:" + user.getUserid());
		return new ResponseEntity<User>(user,HttpStatus.OK);
			}
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") int userid,@RequestBody User user){
		
		if(userDAO.get(userid)==null){
					
			user=new User();
			user.setErrorMsge("user does not exists with id:" + user.getUserid());
			return new ResponseEntity<User> (user,HttpStatus.NOT_FOUND);
		}
		userDAO.update(user);
		
		return new ResponseEntity<User> (user,HttpStatus.OK);		
	}

	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") int userid){
		
		
		User user=userDAO.get(userid);
		
		if(user==null){
			
			user=new User();
			user.setErrorMsge("user does not exists with id:" + userid);
			return new ResponseEntity<User> (user,HttpStatus.NOT_FOUND);	
		}else
		userDAO.delete(user);
		
		return new ResponseEntity<User> (user,HttpStatus.OK);		
	}
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") int userid){
		
		User user=userDAO.get(userid);
		if(user==null){
			
			user=new User();
			
			return new ResponseEntity<User> (user,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User> (user,HttpStatus.OK);
	}

	@RequestMapping(value="/user/authenticate/",method=RequestMethod.POST)
	public ResponseEntity<User> authenticate(@RequestBody User user,HttpSession session){
			
		user=userDAO.isValidUser(user.getEmail(),user.getPassword());	
		if(user==null){
			user=new User();
			user.setErrorMsge("Invalid Credentials!!Please enter valid credentials");
				}
		else{
			user.setErrorMsge("Login Successfull");
			session.setAttribute("loggedInUser",user);
			session.setAttribute("loggedInUserID",user.getUserid());
			
			}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

	@RequestMapping(value="/user/logout",method=RequestMethod.GET)
	public String logOut(HttpSession session){
		
		Integer loogedInUserID=(Integer) session.getAttribute("loggedInUserID");
		
		session.invalidate();
		return("You have successfully logged out");
		
	}

}



