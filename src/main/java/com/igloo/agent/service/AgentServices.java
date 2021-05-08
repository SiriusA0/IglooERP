package com.igloo.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.igloo.agent.model.Agent;

@Service
public class AgentServices {

    @Autowired
    private AgentRepository agentRepo;

    
    public List<Agent> get() {

    	return agentRepo.findAll();
    }

}
