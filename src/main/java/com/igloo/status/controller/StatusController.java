package com.igloo.status.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igloo.status.model.Status;
import com.igloo.status.response.StatusResponse;
import com.igloo.status.service.StatusRepository;
import com.igloo.status.service.StatusService;


@Controller
public class StatusController {
	
	
	@Autowired
    private StatusRepository statusRepository;

    @Autowired
    private StatusService statusService;
	
	
	@GetMapping("/api/status/add")
    @ResponseBody
    public Status add_API(@RequestParam String statusName) {

        return statusService.createStatus(statusName);
    }
	
	@GetMapping("/api/status/get")
	@ResponseBody
	public List<StatusResponse> getAll() {
        return statusService.getAll();
    }
}
