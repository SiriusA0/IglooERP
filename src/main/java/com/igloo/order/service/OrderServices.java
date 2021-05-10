package com.igloo.order.service;

import com.igloo.agent.model.Agent;
import com.igloo.agent.service.AgentRepository;
import com.igloo.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServices {

    @Autowired
    private OrderRepository orderRepo;
    
    
    


    public List<Order> get() {

        return orderRepo.findAll();

    }
    
    public List<Order> ascId() {

        return orderRepo.findAllByOrderByIdAsc();
    }

    public List<Order> descId() {

        return orderRepo.findAllByOrderByIdDesc();
    }
}
