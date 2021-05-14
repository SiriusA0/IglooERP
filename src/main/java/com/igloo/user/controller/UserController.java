package com.igloo.user.controller;

import com.igloo.user.model.User;
import com.igloo.user.service.UserRepository;
import com.igloo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    UserRepository userRepository;


    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) Boolean success) {

        model.addAttribute("success", success);
        return "user/signin";
    }

    @GetMapping("/register")
    public String register() {
        return "user/signup";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password) {
        System.out.println("Login Attempt - User: " + userName + " - Password: " + password);
        if(userService.logUserIn(userName,password)){
            return "redirect:/home?success=true";
        }else{
            return "redirect:/login?success=false";
        }


    }

    @PostMapping("/register")
    public String register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String userName, @RequestParam String email,
                           @RequestParam String telNumber, @RequestParam String jobs, @RequestParam String password, Model model, Boolean error ) {
        System.out.println("Register Attempt");

        User user = new User();
        
        try {
        	
        		String emailToCheck = userRepository.findByEmail(email).getEmail();
        		System.out.println(emailToCheck);
        		String userNameToCheck = userRepository.findByUserName(userName).getUserName();
        		System.out.println(userNameToCheck);
        		
	        	if(emailToCheck == null && userNameToCheck == null) {
	
					 	user.setFirstName(firstName);
		    	        user.setLastName(lastName);
		    	        user.setUserName(userName);
		    	        user.setEmail(email);
		    	        user.setPhoneNumber(telNumber);
		    	        user.setJob(jobs);
		    	        user.setPassword(password);
		    	        userRepository.save(user);
		    	        System.out.println("usuario creado");
	        	}
	    	        
			}catch (Exception e){
				
				model.addAttribute("error", error);
				return "redirect:/register";
			}
			
        
 
        System.out.println("Register Success: " + "-" + user.getFirstName() + "-" + user.getLastName() + "-" + user.getUserName() + "-" + user.getEmail() + "-" + user.getPhoneNumber() + "-" + user.getJob() + "-" + user.getPassword());

        return "redirect:/login?success=true";
    }

}
