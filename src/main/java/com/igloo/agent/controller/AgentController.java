package com.igloo.agent.controller;

import com.igloo.agent.model.Agent;
import com.igloo.agent.service.AgentRepository;
import com.igloo.agent.service.AgentServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AgentController {


    @Autowired
    private AgentRepository agentRepo;
    
    @Autowired
    private AgentServices agentServ;
    

    @GetMapping("/agent")
    public String readAgent(Model model) {
    	
    	List<Agent> agents = agentServ.get();    			
    			
    	model.addAttribute("agents",agents);
    
    
        return "agent/list";
    }

    @PostMapping("/agent")
    public String readAgent_form(String lastName,String firstName) {

    	agentServ.create(lastName,firstName);

        return "redirect:/agent";
    }

//    // Option API
//
//	@GetMapping("/agent")
//	public String find() {
//
//		return "agent/list";
//	}
//
//	@GetMapping("agent/{agent_id}")
//	public String findById(@PathVariable int agent_id) {
//
//		return "agent/detail";
//	}
//
//	@GetMapping("/api/agent")
//	@ResponseBody
//	public List<Agent> find_API() {
//
//		return null;
//	}
//
//	@GetMapping("/api/agent/{agent_id}")
//	@ResponseBody
//	public Agent findById_API(@PathVariable int agent_id) {
//
//		return null;
//	}

}

