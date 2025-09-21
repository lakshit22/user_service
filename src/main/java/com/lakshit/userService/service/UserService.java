package com.lakshit.userService.service;

import java.util.List;

import com.lakshit.userService.model.User;

public interface UserService {
	public User createUser(User user);
	
	public List<User> getUsers();
}
