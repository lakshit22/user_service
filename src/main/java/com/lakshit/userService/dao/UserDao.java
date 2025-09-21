package com.lakshit.userService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lakshit.userService.model.User;

public interface UserDao extends JpaRepository<User, Long>{
}
