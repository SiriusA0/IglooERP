package com.igloo.agent.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.igloo.agent.model.Agent;


@Component
public class AgentAdapter {
	
	public AgentResponse of(Agent agent) {
		
		AgentResponse response = new AgentResponse();
		
		response.setId(agent.getId());
		response.setFirstName(agent.getFirstName());
		response.setLastName(agent.getLastName());
		response.setEmail(agent.getEmail());
		response.setFavorite(agent.getFavorite());
		response.setProfilePic(agent.getProfilePic());

		return response;
		
	}
	
	public List<AgentResponse> of(List<Agent> agents) {
	
        List<AgentResponse> responses = new ArrayList<>();
        for(Agent agent : agents) {
            responses.add(of(agent));
        }
        return responses;      
    }

}
