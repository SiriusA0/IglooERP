package com.igloo.status.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igloo.status.model.Status;
import com.igloo.status.response.StatusAdapter;
import com.igloo.status.response.StatusResponse;

@Service
public class StatusService {

	@Autowired
	StatusRepository statusRepository;
	@Autowired
	StatusAdapter statusAdapter;

	public Status create(String name) {

		Status status = new Status();
		status.setName(name);

		statusRepository.save(status);

		return status;
	}

	public void editStatus(Integer id, String name) {

		Status status = statusRepository.findById(id).get();
		status.setName(name);
		statusRepository.save(status);
	}

	public List<StatusResponse> search() {
		List<Status> status = statusRepository.findAll();

		return statusAdapter.of(status);
	}

	public Boolean delete(String id) {
		Boolean correct = false;
		try {
			int idToDelete = Integer.valueOf(id);
			statusRepository.deleteById(idToDelete);
			correct = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return correct;
	}

}
