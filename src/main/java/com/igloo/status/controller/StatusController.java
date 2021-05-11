package com.igloo.status.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igloo.status.model.Status;
import com.igloo.status.service.StatusRepository;
import com.igloo.status.service.StatusService;


@Controller
public class StatusController {
	
	
	@Autowired
    private StatusRepository statusrepository;

    @Autowired
    private StatusService statusservice;
	
	
	@GetMapping("/api/status/add")
    @ResponseBody
    public Status add_API(@RequestParam String statusName) {

        return statusservice.createStatus(statusName);
    }
}
