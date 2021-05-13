package com.igloo.agent.service;

import java.util.List;

import com.igloo.agent.response.AgentAdapter;
import com.igloo.agent.response.AgentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.igloo.agent.model.Agent;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AgentServices {

    @Autowired
    private AgentRepository agentRepo;

    @Autowired
    private AgentAdapter agentAdapter;

    public List<AgentResponse> get() {

        List<Agent> agents = agentRepo.findAll();
        return agentAdapter.of(agents);


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

    public Agent add(String firstName, String lastName, String email, String profilePic) {
        Agent agent = new Agent();
        agent.setFirstName(firstName);
        agent.setLastName(lastName);
        agent.setEmail(email);
        agent.setProfilePic(profilePic);
        agentRepo.save(agent);
        return agent;
    }

    public void delete(String idtodelete) {

        String idArray[] = idtodelete.split(",");
        for (String i : idArray) {
            int id = Integer.valueOf(i);
            agentRepo.deleteById(id);
        }

    }

}
