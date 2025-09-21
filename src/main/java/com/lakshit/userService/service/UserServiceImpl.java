package com.lakshit.userService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakshit.userService.dao.UserDao;
import com.lakshit.userService.model.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	@Override
	public User createUser(User user) {
		return userDao.save(user);
	}
	
	@Override
	public List<User> getUsers() {
		return userDao.findAll();
	}

}
