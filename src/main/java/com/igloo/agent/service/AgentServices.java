package com.igloo.agent.service;

import java.util.List;

import com.igloo.agent.response.AgentAdapter;
import com.igloo.agent.response.AgentResponse;
import com.igloo.invoice.model.Invoice;
import com.igloo.invoice.response.InvoiceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    /*
    public List<AgentResponse> searchAgent(String action, String option, String term, Integer page) {


        List<Agent> agents = null;

        if (page == null) {
            page = 1;
        }

        if (action == null || action.isEmpty()) {
            Pageable pageable = PageRequest.of(page - 1, 6);
            Page<Agent> lista = agentRepo.findAll(pageable);
            agents = lista.getContent();

        } else if (action.equals("sort")) {
            Sort.Direction direction = Sort.Direction.fromString(option);
            Sort sort = Sort.by(direction, term);
            Pageable pageable = PageRequest.of(page - 1, 6, sort);
            agents = agentRepo.findAll(pageable).getContent();

        } else if (action.equals("search")) {
            if (option.equals("firstName") || option.equals("lastName") ) {
                Pageable pageable = PageRequest.of(page - 1, 6);
                agents = agentRepo.findByFirstNameContainingOrLastNameContaining(term, term, pageable);
            }

        }

       
        return agentAdapter.of(agents);
    }
	*/
    
    public List<AgentResponse> search(String searchTerm) {

         List<Agent> agents = agentRepo.findByFirstNameContainingOrLastNameContaining(searchTerm, searchTerm);
         return agentAdapter.of(agents);
    }

    public List<AgentResponse> ascLastName() {

    	List<Agent> agents = agentRepo.findAllByOrderByLastNameAsc();
        return agentAdapter.of(agents);
    }

    public List<AgentResponse> descLastName() {

    	List<Agent> agents = agentRepo.findAllByOrderByLastNameDesc();
        return agentAdapter.of(agents);
    }

    public List<AgentResponse> ascId() {

    	List<Agent> agents = agentRepo.findAllByOrderByIdAsc();
        return agentAdapter.of(agents);
    }

    public List<AgentResponse> descId() {

    	List<Agent> agents = agentRepo.findAllByOrderByIdDesc();
        return agentAdapter.of(agents);
    }
	
    public Agent createAgent(String firstName, String lastName, String email, String profilePic) {
    	
        Agent agent = new Agent();
        agent.setFirstName(firstName);
        agent.setLastName(lastName);
        agent.setEmail(email);
        agent.setProfilePic(profilePic);
        agentRepo.save(agent);
        return agent;
    }

    public void deleteAgent(String idtodelete) {

        String idArray[] = idtodelete.split(",");
        for (String i : idArray) {
            int id = Integer.valueOf(i);
            agentRepo.deleteById(id);
        }

    }
    
    public void editAgent(Integer id, String firstName, String lastName, String email, String profilePic) {
    	
    	Agent agent = agentRepo.findById(id).get();
    	
    	agent.setFirstName(firstName);
        agent.setLastName(lastName);
        agent.setEmail(email);
        agent.setProfilePic(profilePic);
        agentRepo.save(agent);
    	
    	
    	
    }

}
