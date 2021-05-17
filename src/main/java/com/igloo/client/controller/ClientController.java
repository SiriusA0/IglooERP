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
import com.igloo.invoice.response.InvoiceResponse;


@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;
    
    

    @GetMapping("/client")
    public String readAgent(Model model, Boolean favorite) {

        List<ClientResponse> clients = clientService.search(null,null,null,1);

        model.addAttribute("clients", clients);
        model.addAttribute("favorite", favorite);


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


    @GetMapping("api/client/get")
    @ResponseBody
    public List<ClientResponse> buscador(@RequestParam(required = false) String action,
                                          @RequestParam(required = false) String option,
                                          @RequestParam(required = false) String term, @RequestParam(required = false) Integer page ) {


        List<ClientResponse> client = clientService.search(action, option, term, page);

        return client;
    }

    @GetMapping("/api/client/add")
    @ResponseBody
    public List<ClientResponse> add_API(@RequestParam(required = false) Integer id, @RequestParam char type, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String streetLine1,
    		@RequestParam String streetLine2, @RequestParam Integer cityId, @RequestParam Integer regionId, @RequestParam Integer zipCode,
    		@RequestParam Integer countryId, @RequestParam String idNumber, @RequestParam String phoneNumber1, @RequestParam String phoneNumber2, 
    		@RequestParam String email, @RequestParam String web, @RequestParam String profilePic,@RequestParam Integer categoryId, @RequestParam(required = false) String action,
    		@RequestParam(required = false) String option, @RequestParam(required = false) String term, @RequestParam(required = false) Integer page) {

    	 if(id == null) {
    		 clientService.createClient(type, firstName, lastName, streetLine1, streetLine2, cityId, regionId, zipCode, countryId, idNumber, phoneNumber1, phoneNumber2,
    				 email, web, profilePic, categoryId);
 		 }else {
 			 
 			clientService.editClient(id, type, firstName, lastName, streetLine1, streetLine2, cityId, regionId, zipCode, countryId, idNumber, phoneNumber1, phoneNumber2,
   				 email, web, profilePic, categoryId);
 		 }
    	 
    	 return clientService.search(action, option, term, page);
    }


    @GetMapping("/api/client/delete")
    @ResponseBody
    public List<ClientResponse> delete_API(@RequestParam String id, @RequestParam(required = false) String action,
						            @RequestParam(required = false) String option, @RequestParam(required = false) String term,
						            @RequestParam(required = false) Integer page) {

        clientService.deleteClient(id);
        
        List<ClientResponse>clients = clientService.search(action, option, term, page);
        return clients;
    }
    
    @GetMapping("/api/client/favorite")
    @ResponseBody
    public void favorite_API(@RequestParam Integer id){
    	
    	
    	 clientService.addFavorite(id);
    	
    	
    }



	
	    
}
