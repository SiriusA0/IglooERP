package com.igloo.user.controller;

import com.igloo.sector.response.SectorResponse;
import com.igloo.sector.service.SectorService;
import com.igloo.user.service.UserRepository;
import com.igloo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    SectorService sectorService;

    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) Boolean success) {

        model.addAttribute("success", success);
        return "user/signin";
    }

    @GetMapping("/register")
    public String register(Model model, @RequestParam(required = false) Boolean error) {

        List<SectorResponse> sectors = sectorService.search();
        model.addAttribute("sectors", sectors);
    	model.addAttribute("error", error);
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
        

        	if(userService.create(firstName,lastName,userName, email, telNumber, jobs, password )) {
        		
        		
        		return "redirect:/login?success=true";
        		
        	}else {
        		
        		
    			return "redirect:/register?error=false";
        	}
 
    }

}
