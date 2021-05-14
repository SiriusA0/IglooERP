package com.igloo.status.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igloo.order.response.OrderResponse;
import com.igloo.sector.response.SectorResponse;
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
	
//	
//	@GetMapping("/api/status/add")
//    @ResponseBody
//    public List<StatusResponse> add_API(@RequestParam String name) {
//		statusService.createStatus(name);
//		
//        return statusService.getAll();
//    }
	
	@GetMapping("api/status/add")
	@ResponseBody
	public List<StatusResponse> add_API(@RequestParam(required = false) String id,@RequestParam String name){
	    	
			int idInt=Integer.valueOf(id);
			
	    	if(id == null) {
	    		
	    		statusService.createStatus(name);
	    	} else {
	    		
	    		statusService.editStatus(idInt, name);	
	    	}
	
	System.out.println(statusService.getAll());
	return statusService.getAll();
	}
	
	
	
	
	
	
	
	@GetMapping("/api/status/get")
	@ResponseBody
	public List<StatusResponse> getAll() {
        return statusService.getAll();
    }
	
	@GetMapping("/api/status/delete")
	@ResponseBody
	public List<StatusResponse> delete_API(@RequestParam String id) {
		
	  statusService.delete(id);
	  
	  return statusService.getAll();
	}
	

	
	
}
