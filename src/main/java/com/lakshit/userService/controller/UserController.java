package com.lakshit.userService.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lakshit.userService.model.User;
import com.lakshit.userService.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	private KafkaTemplate<String, String> kafkaTemplate;
	private final Logger log = Logger.getLogger(UserController.class.getName());
	
	public UserController(UserService userService, KafkaTemplate<String, String> kafkaTemplate) {
		this.userService = userService;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<?> creatUser(@RequestBody User user){
		User savedUser = null;
		try {
			savedUser = userService.createUser(user);
		}catch(Exception e) {
			log.info("Error in Fetching Record " + user.getName());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");			
		}
        if (savedUser == null) {
        	log.info("Can't Find user " + user.getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");
        }
        kafkaTemplate.send("user-events", "New user Created: " + savedUser.getName());
        log.info("User Saved Successfully " + user.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	@GetMapping(value = "/getUsers")
	public ResponseEntity<?> getUsers() {
		List<User> users;
		try {
			users = userService.getUsers();
			if(users == null || users.isEmpty()) {
				log.info("User List is Empty");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get users");
			}
		}catch(Exception e) {
			log.info("Error in Fetching Records");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch users");
		}
        log.info("Userlist fetched Successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(users);
	}

}
