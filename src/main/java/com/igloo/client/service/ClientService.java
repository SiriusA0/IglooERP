package com.igloo.client.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igloo.city.service.CityRepository;
import com.igloo.client.model.Client;
import com.igloo.country.service.CountryRepository;
import com.igloo.region.service.RegionRepository;


@Service
public class ClientService {
	@Autowired
    private ClientRepository clientRepository;
	@Autowired
    private CountryRepository countryRepository;
	@Autowired
    private RegionRepository regionRepository;
	@Autowired
    private CityRepository cityRepository;
	
    public List<Client> get() {

        return clientRepository.findAll();

    }

    public List<Client> search(String searchTerm) {

        return clientRepository.findByFirstNameContainingOrLastNameContaining(searchTerm, searchTerm);
    }

    public List<Client> ascLastName() {

        return clientRepository.findAllByOrderByLastNameAsc();
    }

    public List<Client> descLastName() {

        return clientRepository.findAllByOrderByLastNameDesc();
    }

    public List<Client> ascId() {

        return clientRepository.findAllByOrderByIdAsc();
    }

    public List<Client> descId() {

        return clientRepository.findAllByOrderByIdDesc();
    }

    public Client add(char type, String firstName, String lastName, String streetLine1, String streetLine2, Integer cityId, Integer regionId, Integer zipCode,
    		Integer countryId, String idNumber, String phoneNumber1, String phoneNumber2, String email, String web, Integer categoryId) {
        Client client = new Client();
        client.setType(type);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setStreetLine1(streetLine1);
        client.setStreetLine2(streetLine2);
        client.setCity(cityRepository.findById(cityId).get());
        client.setRegion(regionRepository.findById(regionId).get());
        client.setZipCode(zipCode);
        client.setCountry(countryRepository.findById(countryId).get());
        client.setIdNumber(idNumber);
        client.setPhoneNumber1(phoneNumber1);
        client.setPhoneNumber2(phoneNumber2);
        client.setEmail(email);
        client.setWeb(web);


        clientRepository.save(client);
        return client;
    }

    public void delete(String idtodelete) {

        String idArray[] = idtodelete.split(",");
        for (String i : idArray){
            int id = Integer.valueOf(i);
            clientRepository.deleteById(id);
        }

    }
    
    
    
}



