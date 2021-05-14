package com.igloo.status.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igloo.order.model.Order;
import com.igloo.status.model.Status;
import com.igloo.status.response.StatusAdapter;
import com.igloo.status.response.StatusResponse;

@Service
public class StatusService {
	
	@Autowired
	StatusRepository statusRepository;
	@Autowired
	StatusAdapter statusAdapter;
	
	public Status createStatus(String name){
		
		Status status = new Status();
		status.setName(name);
		
		statusRepository.save(status);
		
		return status;
	}
	
	public List<StatusResponse> getAll() {
        List<Status> status = statusRepository.findAll();
        
        return statusAdapter.of(status);
   }
	
	
	public void delete(String id) {
		String idArray[] = id.split(",");
        for (String i : idArray){
            int idToDelete = Integer.valueOf(i);
            statusRepository.deleteById(idToDelete);
        }
	}
	
	
	public void editStatus(Integer id, String name) {
		
		Status status = statusRepository.findById(id).get();
		status.setName(name);
		statusRepository.save(status);
	}
	
	
}
