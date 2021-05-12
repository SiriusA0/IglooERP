package com.igloo.order.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.igloo.client.model.Client;
import com.igloo.client.response.ClientResponse;
import com.igloo.order.model.Order;

@Component
public class OrderAdapter {
	
	
	public OrderResponse of(Order order) {
		
		OrderResponse response = new OrderResponse();
		
		response.setId(order.getId());
		response.setCreationDate(order.getCreationDate());
		response.setAgent(order.getAgent());
		response.setClient(order.getClient());
		response.setSector(order.getSector());
		response.setStatus(order.getStatus());
		response.setTotalAmount(order.getTotalAmount());
		
		return response;
		
	}
	
	public List<OrderResponse> of(List<Order> orders) {
        
        List<OrderResponse> responses = new ArrayList<>();
        for(Order order : orders) {
            responses.add(of(order));
        }
        return responses;      
    }

}
