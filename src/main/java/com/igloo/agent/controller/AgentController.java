package com.igloo.agent.controller;

import com.igloo.agent.model.Agent;
import com.igloo.agent.response.AgentResponse;
import com.igloo.agent.service.AgentRepository;
import com.igloo.agent.service.AgentServices;
import com.igloo.client.response.ClientResponse;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AgentController {

	@Autowired
	private AgentRepository agentRepository;

	@Autowired
	private AgentServices agentService;

	@GetMapping("/agent")
	public String read(Model model) {

		List<AgentResponse> agents = agentService.get();

		model.addAttribute("agents", agents);
		model.addAttribute("title_section", 1);

		return "agent/list";
	}

//    @PostMapping("/agent")
//    public String readAgent_form(String lastName, String firstName) {
//
//        //agentServ.create(lastName,firstName);
//
//        return "redirect:/agent";
//    }

	@GetMapping("/api/agent/add")
	@ResponseBody
	public List<AgentResponse> add_API(@RequestParam(required = false) Integer id, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam String email, @RequestParam String profilePic,
			@RequestParam(required = false) String action, @RequestParam(required = false) String option,
			@RequestParam(required = false) String term, @RequestParam(required = false) Integer page) {

		if (id == null) {

			agentService.create(firstName, lastName, email, profilePic);
		} else {

			agentService.edit(id, firstName, lastName, email, profilePic);

		}

		return agentService.search(action, option, term, page);
	}

	@GetMapping("api/agent/find")
	@ResponseBody
	public AgentResponse find_API(@RequestParam Integer id) {
		return agentService.find(id);
	}

	@GetMapping("api/agent/get")
	@ResponseBody
	public List<AgentResponse> search_API(@RequestParam(required = false) String action,
			@RequestParam(required = false) String option, @RequestParam(required = false) String term,
			@RequestParam(required = false) Integer page) {

		List<AgentResponse> agents = agentService.search(action, option, term, page);

		return agents;
	}

	@GetMapping("/api/agent/delete")
	@ResponseBody
	public List<AgentResponse> delete_API(@RequestParam String id, @RequestParam(required = false) String action,
			@RequestParam(required = false) String option, @RequestParam(required = false) String term,
			@RequestParam(required = false) Integer page) {

		Boolean correct = agentService.delete(id);
		List<AgentResponse> agents;
		if (correct) {
			agents = agentService.search(action,option,term,page);
		} else {
			agents = new LinkedList<AgentResponse>();
		}
		return agents;
	}

	@GetMapping("/api/agent/favorite")
	@ResponseBody
	public void favorite_API(@RequestParam Integer id) {

		agentService.addFavorite(id);

	}

}
