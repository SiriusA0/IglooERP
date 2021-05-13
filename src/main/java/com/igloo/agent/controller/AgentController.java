package com.igloo.agent.controller;

import com.igloo.agent.model.Agent;
import com.igloo.agent.response.AgentResponse;
import com.igloo.agent.service.AgentRepository;
import com.igloo.agent.service.AgentServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AgentController {


    @Autowired
    private AgentRepository agentRepo;

    @Autowired
    private AgentServices agentServ;


    @GetMapping("/agent")
    public String readAgent(Model model) {

        List<AgentResponse> agents = agentServ.get();

        model.addAttribute("agents", agents);


        return "agent/list";
    }

    @PostMapping("/agent")
    public String readAgent_form(String lastName, String firstName) {

        //agentServ.create(lastName,firstName);

        return "redirect:/agent";
    }

    // Option API

//    @GetMapping("/agent")
//    public String find() {
//
//        return "agent/list";
//    }
//
//    @GetMapping("agent/{agent_id}")
//    public String findById(@PathVariable int agent_id) {
//
//        return "agent/detail";
//    }


    @GetMapping("/api/agent/search")
    @ResponseBody
    public List<Agent> find_API(@RequestParam String searchTerm) {
        return agentServ.search(searchTerm);
    }

    @GetMapping("/api/agent/add")
    @ResponseBody
    public Agent add_API(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String profilePic) {

        return agentServ.add(firstName, lastName, email, profilePic);
    }

    @GetMapping("/api/agent/delete")
    @ResponseBody
    public List<AgentResponse> delete_API(@RequestParam String idtodelete) {

        agentServ.delete(idtodelete);
        return agentServ.get();
    }

    @GetMapping("/api/agent/orderbylastnameasc")
    @ResponseBody
    public List<Agent> ascLastName_API() {
        return agentServ.ascLastName();
    }

    @GetMapping("/api/agent/orderbylastnamedesc")
    @ResponseBody
    public List<Agent> descLastName_API() {
        return agentServ.descLastName();
    }

    @GetMapping("/api/agent/orderbyidasc")
    @ResponseBody
    public List<Agent> ascId_API() {
        return agentServ.ascId();
    }

    @GetMapping("/api/agent/orderbyiddesc")
    @ResponseBody
    public List<Agent> descId_API() {
        return agentServ.descId();
    }


}

