package com.igloo.client.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.igloo.client.model.Client;

@Component
public class ClientAdapter {
	
	public ClientResponse of(Client client) {
		
		ClientResponse response = new ClientResponse();
		
		response.setId(client.getId());
		response.setIdNumber(client.getIdNumber());
		response.setFirstName(client.getFirstName());
		response.setLastName(client.getLastName());
		response.setStreetLine1(client.getStreetLine1());
		response.setStreetLine2(client.getStreetLine2());
		response.setPhoneNumber1(client.getPhoneNumber1());
		response.setPhoneNumber2(client.getPhoneNumber2());
		response.setEmail(client.getEmail());
		response.setWeb(client.getWeb());
		response.setProfilePic(client.getProfilePic());
		response.setZipCode(client.getZipCode());
		response.setCountry(client.getCountry());
		response.setRegion(client.getRegion());
		response.setCity(client.getCity());
		response.setCategory(client.getCategory());
		
	
		return response;
	}
	
	public List<ClientResponse> of(List<Client> clients) {
        
        List<ClientResponse> responses = new ArrayList<>();
        for(Client client : clients) {
            responses.add(of(client));
        }
        return responses;      
    }

}
