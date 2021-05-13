package com.igloo.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igloo.client.model.Client;
import com.igloo.client.response.ClientResponse;
import com.igloo.client.service.ClientService;
import com.igloo.country.service.CountryRepository;


@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/client")
    public String readAgent(Model model) {

        List<ClientResponse> clients = clientService.get();

        model.addAttribute("clients", clients);


        return "client/clientlist";
    }

    @PostMapping("/client")
    public String readAgent_form(String lastName, String firstName) {

        //clientService.create(lastName,firstName);

        return "redirect:/client";
    }

    // Option API

//    @GetMapping("/client")
//    public String find() {
//
//        return "client/list";
//    }
//
//    @GetMapping("client/{client_id}")
//    public String findById(@PathVariable int client_id) {
//
//        return "client/detail";
//    }


    @GetMapping("/api/client/search")
    @ResponseBody
    public List<ClientResponse> find_API(@RequestParam String searchTerm) {;
    	return clientService.search(searchTerm);
    }

    @GetMapping("/api/client/add")
    @ResponseBody
    public Client add_API(@RequestParam char type, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String streetLine1,
    		@RequestParam String streetLine2, @RequestParam Integer cityId, @RequestParam Integer regionId, @RequestParam Integer zipCode,
    		@RequestParam Integer countryId, @RequestParam String idNumber, @RequestParam String phoneNumber1, @RequestParam String phoneNumber2, 
    		@RequestParam String email, @RequestParam String web, @RequestParam String profilePic,@RequestParam Integer categoryId) {

        return clientService.add(type, firstName, lastName, streetLine1, streetLine2, cityId, regionId, zipCode, countryId, idNumber,
        		phoneNumber1, phoneNumber2, email, web, profilePic,categoryId);
    }

    /*
    @GetMapping("/api/client/delete")
    @ResponseBody
    public List<Client> delete_API(@RequestParam String idtodelete) {

        clientService.delete(idtodelete);
        return clientService.get();
    }
    */

    @GetMapping("/api/client/orderbylastnameasc")
    @ResponseBody
    public List<ClientResponse> ascLastName_API() {
        return clientService.ascLastName();
    }

    @GetMapping("/api/client/orderbylastnamedesc")
    @ResponseBody
    public List<ClientResponse> descLastName_API() {
        return clientService.descLastName();
    }

    @GetMapping("/api/client/orderbyidasc")
    @ResponseBody
    public List<ClientResponse> ascId_API() {
        return clientService.ascId();
    }

    @GetMapping("/api/client/orderbyiddesc")
    @ResponseBody
    public List<ClientResponse> descId_API() {
        return clientService.descId();
    }
	
	    
}
