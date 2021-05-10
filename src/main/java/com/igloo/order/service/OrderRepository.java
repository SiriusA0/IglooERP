package com.igloo.order.service;

import com.igloo.agent.model.Agent;
import com.igloo.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer> {



}
