package com.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.entity.User;
import com.user.exceptoin.UserException;
import com.user.repo.IUserRepo;

@Service
public class UserService {
	
	@Autowired
	private IUserRepo userRepo;
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	public User createUser(User user) {
		try {
			log.debug("Inside UserService to create User {}",user);
			return userRepo.save(user);
		}catch (Exception e) {
			log.debug("Failed to create User {}",user);
			throw new UserException("Failed to create new User...",e);
		}
		
	}
	
	public User getUserById(long id) {
		
		return userRepo.findById(id)
				.orElseThrow(
						()-> {
						log.debug("Failed to fetch User with Id {}",id);
						 return new UserException("User with Id: "+id+" is does not exist...");
						 });
		
//		try {
//			return userRepo.getById(id);
//		}catch (Exception e) {
//			throw new UserException("User with Id: "+id+" is does not exist...",e);
//	
//		}
		
	}

	
}
