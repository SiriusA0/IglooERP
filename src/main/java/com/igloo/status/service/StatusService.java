package com.igloo.status.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igloo.status.model.Status;

@Service
public class StatusService {
	
	@Autowired
	StatusRepository statusrepository;
	
	public Status createStatus(String name){
		
		Status status = new Status();
		status.setName(name);
		
		statusrepository.save(status);
		
		
		return status;
	}
}
