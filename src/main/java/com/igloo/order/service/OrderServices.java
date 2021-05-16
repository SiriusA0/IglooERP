package com.igloo.order.service;

import com.igloo.agent.model.Agent;
import com.igloo.agent.service.AgentRepository;
import com.igloo.client.model.Client;
import com.igloo.client.response.ClientAdapter;
import com.igloo.client.service.ClientRepository;
import com.igloo.order.model.Order;
import com.igloo.order.response.OrderAdapter;
import com.igloo.order.response.OrderResponse;
import com.igloo.sector.model.Sector;
import com.igloo.sector.service.SectorRepository;
import com.igloo.status.model.Status;
import com.igloo.status.service.StatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private SectorRepository sectorRepository;


    public List<OrderResponse> search(String action, String option, String term, Integer page) {

        List<Order> orders = null;
        if (page == null) {
            page = 1;
        }

        if (action == null || action.isEmpty()) {
            Pageable pageable = PageRequest.of(page - 1, 10);
            Page<Order> pages = orderRepo.findAll(pageable);
            orders = pages.getContent();

        } else if (action.equals("sort")) {
            Sort.Direction direction = Sort.Direction.fromString(option);
            Sort sort = Sort.by(direction, term);
            Pageable pageable = PageRequest.of(page - 1, 10, sort);

            orders = orderRepo.findAll(pageable).getContent();

        } else if (action.equals("search")) {
            if (option.equals("client")) {
                Pageable pageable = PageRequest.of(page - 1, 10);
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


            } else if (option.equals("agent")) {
                Pageable pageable = PageRequest.of(page - 1, 10);
                orders = orderRepo.findByAgentFirstNameContainingOrAgentLastNameContaining(term, term, pageable);
            }

        }

        System.out.println("Orders: " + orders.size());
        System.out.println("Date: " + orders.get(0).getCreationDate());
        return orderadapter.of(orders);
    }


    public List<OrderResponse> createOrder(double totalAmount, Integer statusId, Integer agentId, Integer clientId, Integer sectorId) {

        List<Order> orders = new LinkedList<>();
        Order order_ = new Order();

        order_.setTotalAmount(totalAmount);
        order_.setStatus(statusRepository.findById(statusId).get());
        order_.setAgent(agentRepository.findById(agentId).get());
        order_.setClient(clientRepository.findById(clientId).get());
        order_.setSector(sectorRepository.findById(sectorId).get());

        orderRepo.save(order_);
        orders.add(order_);


        return orderadapter.of(orders);
    }

    public List<OrderResponse> getAll() {

        return orderadapter.of(orderRepo.findAll());

    }

    public void deleteOrder(String idtodelete) {

        String idArray[] = idtodelete.split(",");
        for (String i : idArray) {
            int id = Integer.valueOf(i);
            orderRepo.deleteById(id);
        }

    }

    public OrderResponse findOrder(Integer id) {

        Order order = new Order();

        order = orderRepo.findById(id).get();
        orderRepo.save(order);
        return orderadapter.of(order);
    }

    public void editOrder(Integer id, double totalAmount, Integer statusId, Integer agentId, Integer clientId,
                          Integer sectorId) {

        Order order = orderRepo.findById(id).get();

        order.setTotalAmount(totalAmount);
        order.setStatus(statusRepository.findById(statusId).get());
        order.setAgent(agentRepository.findById(agentId).get());
        order.setClient(clientRepository.findById(clientId).get());
        order.setSector(sectorRepository.findById(sectorId).get());

        orderRepo.save(order);

    }
}
