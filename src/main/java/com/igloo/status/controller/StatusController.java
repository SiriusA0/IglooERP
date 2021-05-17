package com.igloo.status.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igloo.status.response.StatusResponse;
import com.igloo.status.service.StatusService;

@Controller
public class StatusController {

	@Autowired
	private StatusService statusService;

	@GetMapping("api/status/add")
	@ResponseBody
	public List<StatusResponse> add_API(@RequestParam(required = false) String id, @RequestParam String name) {

		if (id == null) {

			statusService.create(name);
		} else {
			int idInt = Integer.valueOf(id);
			statusService.editStatus(idInt, name);
		}

		return statusService.search();
	}

	@GetMapping("/api/status/get")
	@ResponseBody
	public List<StatusResponse> search_API() {
		return statusService.search();
	}

	@GetMapping("/api/status/delete")
	@ResponseBody
	public List<StatusResponse> delete_API(@RequestParam String id) {
		Boolean correct = statusService.delete(id);
		List<StatusResponse> statuses;
		if (correct) {
			statuses = statusService.search();
		} else {
			statuses = new LinkedList<StatusResponse>();
		}
		return statuses;
	}

}
