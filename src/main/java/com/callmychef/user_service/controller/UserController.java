package com.callmychef.user_service.controller;

import com.callmychef.user_service.chefdb.entity.Chef;
import com.callmychef.user_service.dto.mapper.ChefProfileDTO;
import com.callmychef.user_service.userdb.entity.User;
import com.callmychef.user_service.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/chef/createchef")
    public void createChef(@RequestBody ChefProfileDTO chefProfileDto) { userService.createChef(chefProfileDto);}
}
