package com.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entity.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public List<User> getUsers(){
		return userDao.getUsers();
	}
	
	public List<User> getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	public void save(User user) {
		userDao.save(user);
	}

	public void removeFromDepartment(int id) {
		userDao.removeFromDepartment(id);
	}

}
