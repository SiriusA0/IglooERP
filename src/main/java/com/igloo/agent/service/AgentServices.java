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

    public List<Agent> search(String searchTerm) {

        return agentRepo.findByFirstNameContainingOrLastNameContaining(searchTerm, searchTerm);
    }

    public List<Agent> ascLastName() {

        return agentRepo.findAllByOrderByLastNameAsc();
    }

    public List<Agent> descLastName() {

        return agentRepo.findAllByOrderByLastNameDesc();
    }

    public List<Agent> ascId() {

        return agentRepo.findAllByOrderByIdAsc();
    }

    public List<Agent> descId() {

        return agentRepo.findAllByOrderByIdDesc();
    }
}
