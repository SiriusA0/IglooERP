package com.igloo.order.service;

import com.igloo.agent.model.Agent;
import com.igloo.agent.service.AgentRepository;
import com.igloo.client.model.Client;
import com.igloo.client.response.ClientAdapter;
import com.igloo.client.service.ClientRepository;
import com.igloo.order.model.Order;
import com.igloo.order.response.OrderAdapter;
import com.igloo.order.response.OrderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServices {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private OrderAdapter orderadapter;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientAdapter clientAdapter;
    
    
    public List<OrderResponse> search(String action, String option, String term){
    	
    	List<Order> orders = null;
    	
    	if(action == null || action.isEmpty()) {
    		
    		orders = orderRepo.findAll();
    		
    	} else if(action == "sort") {
    		
    		
    		orders = orderRepo.findAll(Sort.by(Sort.Direction.fromString(term),option));
    		
    	}else if(action == "search") {
    		
    		if(option == "client") {
    			
    			List <Client> clients = clientRepository.findByFirstNameContainingOrLastNameContaining(term, term);
    			
    			
    			
    			for(Client client : clients) {
    				
    				orders = orderRepo.findByClient(client);
    				
    			}
    			
    			
    		}
    		
    		
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	return orderadapter.of(orders);
    }
    


    public List<Order> get() {

        return orderRepo.findAll();
   }
//    
//    public List<Order> ascId() {
//
//        return orderRepo.findAllByOrderByIdAsc();
//    }
//
//    public List<Order> descId() {
//
//        return orderRepo.findAllByOrderByIdDesc();
//    }
//    
//    public List<Order> descAmount(){
//    	
//    	return orderRepo.findAll();
//    	
//    }
//    
    
}
