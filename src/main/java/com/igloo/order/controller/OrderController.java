package com.igloo.order.controller;

import com.igloo.agent.response.AgentResponse;
import com.igloo.agent.service.AgentServices;
import com.igloo.client.response.ClientResponse;
import com.igloo.client.service.ClientService;
import com.igloo.order.response.OrderResponse;
import com.igloo.order.service.OrderServices;
import com.igloo.sector.response.SectorResponse;
import com.igloo.sector.service.SectorService;
import com.igloo.status.response.StatusResponse;
import com.igloo.status.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {

	@Autowired
	private OrderServices orderService;

	@Autowired
	private StatusService statusesService;

	@Autowired
	private SectorService sectorsService;

	@Autowired
	private ClientService clientsService;

	@Autowired
	private AgentServices agentsService;

	@GetMapping("/order")
	public String read(Model model) {

		List<OrderResponse> orders = orderService.search(null, null, null, 1);
		List<StatusResponse> statuses = statusesService.search();
		List<SectorResponse> sectors = sectorsService.search();
		List<ClientResponse> clients = clientsService.get();
		List<AgentResponse> agents = agentsService.get();

		model.addAttribute("orders", orders);
		model.addAttribute("statuses", statuses);
		model.addAttribute("sectors", sectors);
		model.addAttribute("clients", clients);
		model.addAttribute("agents", agents);

		return "order/orderlist";
	}

	@GetMapping("api/order/add")
	@ResponseBody
	public List<OrderResponse> add_API(@RequestParam(required = false) Integer id, @RequestParam double totalAmount,
			@RequestParam Integer statusId, @RequestParam Integer agentId, @RequestParam Integer clientId,
			@RequestParam Integer sectorId, @RequestParam(required = false) String action,
			@RequestParam(required = false) String option, @RequestParam(required = false) String term,
			@RequestParam(required = false) Integer page) {

		if (id == null) {

			orderService.create(totalAmount, statusId, agentId, clientId, sectorId);
		} else {

			orderService.edit(id, totalAmount, statusId, agentId, clientId, sectorId);
		}
		return orderService.search(action, option, term, page);
	}

	@GetMapping("api/order/find")
	@ResponseBody
	public OrderResponse find_API(@RequestParam Integer id) {
		return orderService.find(id);
	}

	@GetMapping("api/order/get")
	@ResponseBody
	public List<OrderResponse> search_API(@RequestParam(required = false) String action,
			@RequestParam(required = false) String option, @RequestParam(required = false) String term,
			@RequestParam(required = false) Integer page) {

		List<OrderResponse> orders = orderService.search(action, option, term, page);
		return orders;
	}

	@GetMapping("/api/order/delete")
	@ResponseBody
	public List<OrderResponse> delete_API(@RequestParam String id, @RequestParam(required = false) String action,
			@RequestParam(required = false) String option, @RequestParam(required = false) String term,
			@RequestParam(required = false) Integer page) {

		orderService.delete(id);
		List<OrderResponse> orders = orderService.search(action, option, term, page);
		return orders;
	}

}
