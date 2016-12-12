package com.task.doa;

import java.util.List;

import com.task.model.User;

public interface UserDAO {
	
public boolean save(User user); 
	
	public boolean update(User user);
	
	public boolean delete(User user);
	
	public User get(int userID);
	
	public User getName(String username);
	
	public List<User> list();
	
	public User isValidUser(String email, String password);
	


}
