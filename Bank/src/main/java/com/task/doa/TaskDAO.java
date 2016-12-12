package com.task.doa;

import java.util.List;

import com.task.model.Task;

public interface TaskDAO {

	
public boolean save(Task task); 
	
	public boolean update(Task task);
	
	public boolean delete(Task task);
	
	public Task get(int taskid);
	
	public Task getByUser(int userid);
	
	public List<Task> list();
}
