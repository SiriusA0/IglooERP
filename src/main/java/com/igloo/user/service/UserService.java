package com.igloo.user.service;

import com.igloo.order.model.Order;
import com.igloo.user.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;
    
    public boolean createUser(String firstName,  String lastName, String userName,  String email,
             String telNumber,  String jobs, String password) {
    	
    	 User user = new User();
    	
    	if ( userRepository.findByUserName(userName) == null  &&  userRepository.findByEmail(email) == null) {
    		
    		user.setFirstName(firstName);
	        user.setLastName(lastName);
	        user.setUserName(userName);
	        user.setEmail(email);
	        user.setPhoneNumber(telNumber);
	        user.setJob(jobs);
	        user.setPassword(password);
	        userRepository.save(user);
	        System.out.println("usuario creado");
	        
	        return true;
    	}
    	
    	
    	return false;
    }

 

    public boolean logUserIn(String userName, String password) {

    	
    	User user = userRepository.findByUserName(userName);
		if(user.getPassword().equals(password)  ) {
			return true;
		}

    	
    	return false;
  
    }

}
