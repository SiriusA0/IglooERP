package com.igloo.order.response;

import java.util.ArrayList;
import java.util.List;

import com.igloo.agent.response.AgentAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.igloo.agent.model.Agent;
import com.igloo.client.model.Client;
import com.igloo.client.response.ClientAdapter;
import com.igloo.order.model.Order;
import com.igloo.sector.model.Sector;
import com.igloo.sector.response.SectorAdapter;
import com.igloo.status.model.Status;
import com.igloo.status.response.StatusAdapter;

@Component
public class OrderAdapter {

	@Autowired
	ClientAdapter clientAdapter;
	@Autowired
	StatusAdapter statusAdapter;
	@Autowired
	SectorAdapter sectorAdapter;
	@Autowired
	AgentAdapter agentAdapter;

	public OrderResponse of(Order order) {

		OrderResponse response = new OrderResponse();

		response.setId(order.getId());

		String date = order.getCreationDate().toString().split(" ")[0];

		response.setCreationDate(date);

		Agent agent = order.getAgent();
		response.setAgent(agentAdapter.of(agent));

		Client client = order.getClient();
		response.setClient(clientAdapter.of(client));

		Status status = order.getStatus();
		response.setStatus(statusAdapter.of(status));

		Sector sector = order.getSector();
		response.setSector(sectorAdapter.of(sector));

		response.setTotalAmount(order.getTotalAmount());

		return response;

	}

	public List<OrderResponse> of(List<Order> orders) {

		List<OrderResponse> responses = new ArrayList<>();

		for (Order order : orders) {
			responses.add(of(order));
		}
		return responses;
	}

}
