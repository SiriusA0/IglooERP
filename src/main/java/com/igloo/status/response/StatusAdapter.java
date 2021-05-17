package com.igloo.status.response;

import java.util.ArrayList;
import java.util.List;

import com.igloo.status.model.Status;

import org.springframework.stereotype.Component;

@Component
public class StatusAdapter {

	public StatusResponse of(Status status) {

		StatusResponse response = new StatusResponse();

		response.setId(status.getId());
		response.setName(status.getName());

		return response;
	}

	public List<StatusResponse> of(List<Status> statuses) {

		List<StatusResponse> responses = new ArrayList<>();
		for (Status status : statuses) {
			responses.add(of(status));
		}
		return responses;

	}
}
