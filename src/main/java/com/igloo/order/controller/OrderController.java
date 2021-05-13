package com.igloo.order.controller;

import com.igloo.agent.model.Agent;
import com.igloo.agent.response.AgentResponse;
import com.igloo.agent.service.AgentServices;
import com.igloo.client.model.Client;
import com.igloo.client.response.ClientResponse;
import com.igloo.client.service.ClientService;
import com.igloo.order.model.Order;
import com.igloo.order.response.OrderResponse;
import com.igloo.order.service.OrderRepository;
import com.igloo.order.service.OrderServices;
import com.igloo.sector.model.Sector;
import com.igloo.sector.response.SectorResponse;
import com.igloo.sector.service.SectorService;
import com.igloo.status.model.Status;
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
    private OrderRepository orderRepo;

    @Autowired
    private OrderServices orderServ;

    @Autowired
    private StatusService statusesService;

    @Autowired
    private SectorService sectorsService;

    @Autowired
    private ClientService clientsService;

    @Autowired
    private AgentServices agentsService;


    @GetMapping("/order")
    public String readOrder(Model model) {

        List<OrderResponse> orders = orderServ.search(null, null, null);//TODO unificar nombres
        List<StatusResponse> statuses = statusesService.getAll();//TODO unificar nombres
        List<SectorResponse> sectors = sectorsService.showSector();//TODO unificar nombres
        List<ClientResponse> clients = clientsService.get();//TODO unificar nombres
        List<AgentResponse> agents = agentsService.get();//TODO unificar nombres

        model.addAttribute("orders", orders);
        model.addAttribute("statuses", statuses);
        model.addAttribute("sectors", sectors);
        model.addAttribute("clients", clients);
        model.addAttribute("agents", agents);

        return "order/orderlist";
    }


    @GetMapping("api/order/get")
    @ResponseBody
    public List <OrderResponse> buscador(@RequestParam(required = false) String action,
    										@RequestParam(required = false) String option,
    										@RequestParam(required = false) String term ){
    	

    	List<OrderResponse> orders = orderServ.search(action, option, term);

    	return orders;
    }
    
    @GetMapping("api/order/add")
    @ResponseBody
    public List<OrderResponse> add_API(@RequestParam(required = false) Integer id,@RequestParam double totalAmount,@RequestParam Integer statusId, 
    		@RequestParam Integer agentId, @RequestParam Integer clientId, @RequestParam Integer sectorId){
    	
    	if(id == null) {
    		
    		orderServ.createOrder(totalAmount, statusId, agentId, clientId, sectorId);
    	} else {
    		
    		orderServ.editOrder(id, totalAmount, statusId, agentId, clientId, sectorId);	
    	}
    	
    	
    	
    	return orderServ.getAll();
    }
    
    @GetMapping("/api/order/delete")
    @ResponseBody
    public List<OrderResponse> delete_API(@RequestParam String orderId) {

    	orderServ.deleteOrder(orderId);
        return orderServ.getAll();
    }
    
    @GetMapping("api/order/find")
    @ResponseBody
    public OrderResponse find_API(@RequestParam Integer id) {
    	
    	
    	return orderServ.findOrder(id);
    }
}

