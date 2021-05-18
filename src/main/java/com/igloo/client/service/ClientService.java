package com.igloo.client.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.igloo.category.service.CategoryRepository;
import com.igloo.city.service.CityRepository;
import com.igloo.client.model.Client;
import com.igloo.client.response.ClientAdapter;
import com.igloo.client.response.ClientResponse;
import com.igloo.country.service.CountryRepository;
import com.igloo.order.model.Order;
import com.igloo.order.response.OrderResponse;
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
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ClientAdapter clientadapter;

    public List<ClientResponse> get() {

        List<Client> clients = clientRepository.findAll();

        return clientadapter.of(clients);
    }

    public ClientResponse create(char type, String firstName, String lastName, String streetLine1, String streetLine2,
                                 Integer cityId, Integer regionId, Integer zipCode, Integer countryId, String idNumber, String phoneNumber1,
                                 String phoneNumber2, String email, String web, String profilePic, Integer categoryId) {
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
        client.setProfilePic(profilePic);
        client.setCategory(categoryRepository.findById(categoryId).get());

        clientRepository.save(client);
        return clientadapter.of(client);
    }

    public void edit(Integer id, char type, String firstName, String lastName, String streetLine1, String streetLine2,
                     Integer cityId, Integer regionId, Integer zipCode, Integer countryId, String idNumber, String phoneNumber1,
                     String phoneNumber2, String email, String web, String profilePic, Integer categoryId) {

        Client client = clientRepository.findById(id).get();

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
        client.setProfilePic(profilePic);
        client.setCategory(categoryRepository.findById(categoryId).get());

        clientRepository.save(client);

    }
    
    public ClientResponse find(Integer id) {

    	Client client = new Client();

    	client = clientRepository.findById(id).get();
    	clientRepository.save(client);
		return clientadapter.of(client);
	}
    

    public List<ClientResponse> search(String action, String option, String term, Integer page) {

        List<Client> clients = new LinkedList<>();

        if (page == null) {
            page = 1;
        }

        if (action == null || action.isEmpty()) {
            Pageable pageable = PageRequest.of(page - 1, 6);
            Page<Client> lista = clientRepository.findAll(pageable);
            clients = lista.getContent();

        } else if (action.equals("sort")) {
            Sort.Direction direction = Sort.Direction.fromString(option);
            Sort sort = Sort.by(direction, term);
            Pageable pageable = PageRequest.of(page - 1, 6, sort);
            clients = clientRepository.findAll(pageable).getContent();

        } else if (action.equals("search")) {
            if (option.equals("client")) {
                Pageable pageable = PageRequest.of(page - 1, 6);
                clients = clientRepository.findByFirstNameContainingOrLastNameContaining(term, term, pageable);

            } else if (option.equals("favorite")) {

                Pageable pageable = PageRequest.of(page - 1, 6);
                clients = clientRepository.findByFavoriteIs(true, pageable);

            }

        }

        return clientadapter.of(clients);
    }

    public void delete(String idtodelete) {

        String idArray[] = idtodelete.split(",");
        for (String i : idArray) {
            int id = Integer.valueOf(i);
            clientRepository.deleteById(id);
        }

    }

    public void addFavorite(Integer id) {

        Client client = clientRepository.findById(id).get();

        if (client.getFavorite() == null || !client.getFavorite()) {

            client.setFavorite(true);
        } else {

            client.setFavorite(false);
        }

        clientRepository.save(client);

    }

}
