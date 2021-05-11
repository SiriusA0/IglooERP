package com.igloo.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.igloo.agent.model.Agent;
import com.igloo.client.model.Client;
import com.igloo.client.service.ClientRepository;
import com.igloo.client.service.ClientService;


@Controller
public class ClientController {
	

	    @Autowired
	    private ClientService clientServ;
	    
	    @GetMapping("/client")
	    public String readAgent(Model model) {

	        List<Client> clients = clientServ.get();

	        model.addAttribute("clients", clients);


	        return "client/clientlist";
	    }
	
	
	    
	    
	    
	    
	    
	    
	    
}
