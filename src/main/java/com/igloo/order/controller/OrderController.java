package com.igloo.order.controller;

import com.fasterxml.classmate.Annotations;
import com.igloo.agent.model.Agent;
import com.igloo.agent.service.AgentRepository;
import com.igloo.agent.service.AgentServices;
import com.igloo.order.model.Order;
import com.igloo.order.response.OrderResponse;
import com.igloo.order.service.OrderRepository;
import com.igloo.order.service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {


    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderServices orderServ;

    @GetMapping("/order")
    public String readOrder(Model model) {

        List<Order> orders = orderServ.get();

        model.addAttribute("orders", orders);

        return "order/orderlist";
    }
    
    @GetMapping("api/order/get")
    @ResponseBody
    public List <OrderResponse> buscador(@RequestParam(required = false) String action,
    										@RequestParam(required = false) String option,
    										@RequestParam(required = false) String term ){
    	
    	
    	
    	
    	return orderServ.search(action, option, term);
    }
    
  
    @GetMapping("/api/order/orderbyamountdesc")
    @ResponseBody
    public List<Order> descAmount_API() {
    	
    	
        return orderRepo.findAll(Sort.by(Sort.Direction.DESC, "totalAmount"));
    }
    
    @GetMapping("/api/order/orderbyamountasc")
    @ResponseBody
    public List<Order> ascAmount_API() {
    	
    	
        return orderRepo.findAll(Sort.by(Sort.Direction.ASC, "totalAmount"));
    }

}
