package com.igloo.order.controller;

import com.fasterxml.classmate.Annotations;
import com.igloo.agent.model.Agent;
import com.igloo.agent.service.AgentRepository;
import com.igloo.agent.service.AgentServices;
import com.igloo.order.model.Order;
import com.igloo.order.service.OrderRepository;
import com.igloo.order.service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
