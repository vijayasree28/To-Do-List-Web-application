package com.task.doa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.model.Task;


@SuppressWarnings("deprecation")
@Repository(value = "TaskDAO")
public class TaskDAOImpl implements TaskDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public TaskDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Transactional

	
	public boolean save(Task task) {
		try {
			sessionFactory.getCurrentSession().save(task);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	

	public boolean update(Task task) {
		try {
			sessionFactory.getCurrentSession().update(task);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	@Transactional

	public boolean delete(Task task) {
		try {
			sessionFactory.getCurrentSession().delete(task);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Task get(int taskid) {
		String hql = "from Task where taskid=" + taskid ;

		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Task> list = (List<Task>) query.list();

		if (list != null && !list.isEmpty()) {
			System.out.println("task will get from DAOImpl");
			return list.get(0);
		} else {
			return null;
		}
	}
	@Transactional
	public Task getByUser(int userid) {
		String hql = "from Task where userid=" + userid;
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Task> list = (List<Task>) query.list();

		if (list != null && !list.isEmpty()) {
			System.out.println("task by userid retrieved from DAOImpl");
			return list.get(0);
		} else {
			return null;
		}
	}
		
		@Transactional
	public List<Task> list() {
			String hql = " from Task";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return query.list();

		
	}

}
