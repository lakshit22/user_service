package com.callmychef.user_service.service;

import com.callmychef.user_service.chefdb.entity.Chef;
import com.callmychef.user_service.dto.mapper.ChefProfileDTO;
import com.callmychef.user_service.userdb.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User createUser(User user);
    public void createChef(ChefProfileDTO chefProfileDto);
}
