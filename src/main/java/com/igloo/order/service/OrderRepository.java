package com.igloo.order.service;

import com.igloo.agent.model.Agent;
import com.igloo.client.model.Client;
import com.igloo.order.model.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer> {


	List<Order> findAllByOrderByIdAsc();
    List<Order> findAllByOrderByIdDesc();
    List<Order> findByClient(Client client);

}
