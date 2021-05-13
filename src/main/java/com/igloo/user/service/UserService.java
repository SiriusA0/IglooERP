package com.igloo.user.service;

import com.igloo.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    public User createUser(String firstName, String lastName, String userName, String email,
                           String telNumber, String jobs, String password) {

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setEmail(email);
        user.setPhoneNumber(telNumber);
        user.setJob(jobs);
        user.setPassword(password);
        userRepository.save(user);

        return user;
    }

    public boolean logUserIn(String userName, String password) {

        User user = userRepository.findByUserName(userName);
        if (user.getPassword().equals(password)) {
            System.out.println("Login Success - User: " + userName + " - Password: " + password);
            return true;
        }
        return false;
    }

}
