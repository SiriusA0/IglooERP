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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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
    private AgentRepository agentRepository;
    @Autowired
    private ClientAdapter clientAdapter;
    
    
    public List<OrderResponse> search(String action, String option, String term){
    	
    	List<Order> orders = null;
    	
    	if(action == null || action.isEmpty()) {
			Pageable pageable = PageRequest.of(0, 25);
			Page<Order> lista = orderRepo.findAll(pageable);
			orders = lista.getContent();

		} else if(action.equals("sort")) {
			Sort.Direction direction = Sort.Direction.fromString(option);
			Sort sort = Sort.by(direction,term);
			Pageable pageable = PageRequest.of(0, 25, sort);

    		orders = orderRepo.findAll(pageable).getContent();
    		
    	}else if(action.equals("search")) {
    		if(option.equals("client")) {
				Pageable pageable = PageRequest.of(0, 25);
    			orders = orderRepo.findByClientFirstNameContainingOrClientLastNameContaining(term, term, pageable);
    			
    			//List <Client> clients = clientRepository.findByFirstNameContainingOrLastNameContaining(term, term);
    			
    			// SELECT * FROM order O 
    			// Left join CLIENT c ON (o.client = c.id_client)
    			// WHERE c.firstName LIKE '%'param'%'´
    		
    			
    			//orders = new LinkedList<>();
    			//System.out.println(clients.size());
    			//for(Client client : clients) {
    				
    			//	for(Order order : client.getOrders()) {
    					
    			//		orders.add(order);
    			//	}
    			//}
    			
    			
    		}else if(option.equals("agent")) {
    			List <Agent> agents = agentRepository.findByFirstNameContainingOrLastNameContaining(term, term);
    			orders = new LinkedList<>();
    			for(Agent agent : agents) {
    				for(Order order : agent.getOrders()) {
    					orders.add(order);
    				}
    			}	
    		}
  
    	}
    	
    	System.out.println("Orders: "+orders.size());
    	
    	return orderadapter.of(orders);
    }
}
