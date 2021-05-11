package com.igloo.client.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.igloo.client.model.Client;


@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepo;

    public List<Client> get() {

        return  clientRepo.findAll();

    }
    
    
    
}



