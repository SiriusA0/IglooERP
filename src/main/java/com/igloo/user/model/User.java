package com.igloo.user.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="user_name")
    private String userName;
    
    @Column
    private String email;
    
    @Column(name="phone_number")
    private String phoneNumber;
    
    @Column
    private String job;
    
    @Column
    private String password;
    
    @Column(name="profile_pic")
    private String profilePic;

    // Favorites

    @Column(name="fav_agents")
    private String favAgents;

    @Column(name="fav_clients")
    private String favClients;
    
	public User(String firstName, String lastName, String userName, String email, String phoneNumber, String job,
			String password, String profilePic, String favAgents, String favClients) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.job = job;
		this.password = password;
		this.profilePic = profilePic;
		this.favAgents = favAgents;
		this.favClients = favClients;
	}

	public User(int id, String firstName, String lastName, String userName, String email, String phoneNumber,
			String job, String password, String profilePic, String favAgents, String favClients) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.job = job;
		this.password = password;
		this.profilePic = profilePic;
		this.favAgents = favAgents;
		this.favClients = favClients;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getFavAgents() {
		return favAgents;
	}

	public void setFavAgents(String favAgents) {
		this.favAgents = favAgents;
	}

	public String getFavClients() {
		return favClients;
	}

	public void setFavClients(String favClients) {
		this.favClients = favClients;
	}
    
    
}
