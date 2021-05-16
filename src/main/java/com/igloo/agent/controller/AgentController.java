package com.igloo.agent.controller;

import com.igloo.agent.model.Agent;
import com.igloo.agent.response.AgentResponse;
import com.igloo.agent.service.AgentRepository;
import com.igloo.agent.service.AgentServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AgentController {


    @Autowired
    private AgentRepository agentRepo;

    @Autowired
    private AgentServices agentServ;


    @GetMapping("/agent")
    public String readAgent(Model model) {

        List<AgentResponse> agents = agentServ.get();

        model.addAttribute("agents", agents);


        return "agent/list";
    }

    @PostMapping("/agent")
    public String readAgent_form(String lastName, String firstName) {

        //agentServ.create(lastName,firstName);

        return "redirect:/agent";
    }

    // Option API

//    @GetMapping("/agent")
//    public String find() {
//
//        return "agent/list";
//    }
//
//    @GetMapping("agent/{agent_id}")
//    public String findById(@PathVariable int agent_id) {
//
//        return "agent/detail";
//    }


//    @GetMapping("/api/agent/search")
//    @ResponseBody
//    public List<AgentResponse> find_API(@RequestParam String searchTerm) {
//        return agentServ.search(searchTerm);
//    }

    @GetMapping("/api/agent/add")
    @ResponseBody
    public List<AgentResponse> add_API(@RequestParam(required = false)Integer id,@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String profilePic) {

    	if(id == null) {
    		
    		agentServ.createAgent(firstName, lastName, email, profilePic);	
    	}else {
    		
    		agentServ.editAgent(id, firstName, lastName, email, profilePic);
    		
    	}
    	
        return agentServ.get();
    }

    @GetMapping("/api/agent/delete")
    @ResponseBody
    public List<AgentResponse> delete_API(@RequestParam String idtodelete) {

        agentServ.deleteAgent(idtodelete);
        return agentServ.get();
    }

    @GetMapping("/api/agent/orderbylastnameasc")
    @ResponseBody
    public List<AgentResponse> ascLastName_API() {
        return agentServ.ascLastName();
    }

    @GetMapping("/api/agent/orderbylastnamedesc")
    @ResponseBody
    public List<AgentResponse> descLastName_API() {
        return agentServ.descLastName();
    }

    @GetMapping("/api/agent/orderbyidasc")
    @ResponseBody
    public List<AgentResponse> ascId_API() {
        return agentServ.ascId();
    }

    @GetMapping("/api/agent/orderbyiddesc")
    @ResponseBody
    public List<AgentResponse> descId_API() {
        return agentServ.descId();
    }


}

