package com.igloo.agent.controller;

import com.igloo.agent.model.Agent;
import com.igloo.agent.response.AgentResponse;
import com.igloo.agent.service.AgentRepository;
import com.igloo.agent.service.AgentServices;
import com.igloo.client.response.ClientResponse;

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
    
    @GetMapping("api/agent/get")
    @ResponseBody
    public List<AgentResponse> buscador(@RequestParam(required = false) String action,
                                          @RequestParam(required = false) String option,
                                          @RequestParam(required = false) String term, @RequestParam(required = false) Integer page ) {


        List<AgentResponse> agents = agentServ.search(action, option, term, page);

        return agents;
    }



    @GetMapping("/api/agent/add")
    @ResponseBody
    public List<AgentResponse> add_API(@RequestParam(required = false)Integer id,
    		@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String profilePic,
    		@RequestParam(required = false) String action,
    		@RequestParam(required = false) String option, @RequestParam(required = false) String term, @RequestParam(required = false) Integer page) {

    	if(id == null) {
    		
    		agentServ.createAgent(firstName, lastName, email, profilePic);	
    	}else {
    		
    		agentServ.editAgent(id, firstName, lastName, email, profilePic);
    		
    	}
    	
        return agentServ.search(action, option,term,page);
    }
    
    @GetMapping("/api/agent/favorite")
    @ResponseBody
    public void favorite_API(@RequestParam Integer id){
    	
    	 agentServ.addFavorite(id);
    	
    	
    }

    @GetMapping("/api/agent/delete")
    @ResponseBody
    public List<AgentResponse> delete_API(@RequestParam String id, @RequestParam(required = false) String action,
    		@RequestParam(required = false) String option, @RequestParam(required = false) String term, @RequestParam(required = false) Integer page) {

        agentServ.deleteAgent(id);
        return agentServ.search(action, option,term,page);
    }



}

