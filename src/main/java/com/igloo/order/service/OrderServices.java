package com.igloo.order.service;

import com.igloo.agent.service.AgentRepository;
import com.igloo.client.service.ClientRepository;
import com.igloo.order.model.Order;
import com.igloo.order.response.OrderAdapter;
import com.igloo.order.response.OrderResponse;
import com.igloo.sector.service.SectorRepository;
import com.igloo.status.service.StatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrderServices {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderAdapter orderAdapter;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private SectorRepository sectorRepository;

	public List<OrderResponse> create(double totalAmount, Integer statusId, Integer agentId, Integer clientId,
			Integer sectorId) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date = calendar.getTime();

		List<Order> orders = new LinkedList<>();
		Order order = new Order();

		order.setTotalAmount(totalAmount);
		order.setCreationDate(date);
		order.setStatus(statusRepository.findById(statusId).get());
		order.setAgent(agentRepository.findById(agentId).get());
		order.setClient(clientRepository.findById(clientId).get());
		order.setSector(sectorRepository.findById(sectorId).get());

		orderRepository.save(order);
		orders.add(order);

		return orderAdapter.of(orders);
	}

	public void edit(Integer id, double totalAmount, Integer statusId, Integer agentId, Integer clientId,
			Integer sectorId) {
		Order order = orderRepository.findById(id).get();

		order.setTotalAmount(totalAmount);
		order.setStatus(statusRepository.findById(statusId).get());
		order.setAgent(agentRepository.findById(agentId).get());
		order.setClient(clientRepository.findById(clientId).get());
		order.setSector(sectorRepository.findById(sectorId).get());

		orderRepository.save(order);

	}

	public OrderResponse find(Integer id) {

		Order order = new Order();

		order = orderRepository.findById(id).get();
		orderRepository.save(order);
		return orderAdapter.of(order);
	}

	public List<OrderResponse> search(String action, String option, String term, Integer page) {

		List<Order> orders = null;
		if (page == null) {
			page = 1;
		}

		if (action == null || action.isEmpty()) {
			Pageable pageable = PageRequest.of(page - 1, 10);
			Page<Order> pages = orderRepository.findAll(pageable);
			orders = pages.getContent();

		} else if (action.equals("sort")) {

			Sort.Direction direction = Sort.Direction.fromString(option);
			Sort sort = Sort.by(direction, term);
			Pageable pageable = PageRequest.of(page - 1, 10, sort);

			orders = orderRepository.findAll(pageable).getContent();

		} else if (action.equals("search")) {
			if (option.equals("client")) {
				Pageable pageable = PageRequest.of(page - 1, 10);
				orders = orderRepository.findByClientFirstNameContainingOrClientLastNameContaining(term, term,
						pageable);
			} else if (option.equals("agent")) {
				Pageable pageable = PageRequest.of(page - 1, 10);
				orders = orderRepository.findByAgentFirstNameContainingOrAgentLastNameContaining(term, term, pageable);
			}
		}
		return orderAdapter.of(orders);
	}

	public void delete(String idtodelete) {

		String idArray[] = idtodelete.split(",");
		for (String i : idArray) {
			int id = Integer.valueOf(i);
			orderRepository.deleteById(id);
		}

	}

}
