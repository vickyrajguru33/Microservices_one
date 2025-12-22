package com.user.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.service.UserService;

@RestController
public class UserController {
	
	public static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/add-user")
	public User createUserAccount(@RequestBody User user) {
		log.info("Inside the Create User Method..{}", user.getEmail());
		return userService.createUser(user);
	}
	
	@GetMapping("/get-user/{id}")
	public User getUserById(@PathVariable long id) {
		log.info("Fetching User with Id :{}"+id);
		return userService.getUserById(id);
	}
	

}
