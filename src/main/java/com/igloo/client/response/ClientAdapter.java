package com.igloo.client.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.igloo.category.model.Category;
import com.igloo.category.response.CategoryAdapter;
import com.igloo.city.model.City;
import com.igloo.city.response.CityAdapter;
import com.igloo.client.model.Client;
import com.igloo.country.model.Country;
import com.igloo.country.response.CountryAdapter;
import com.igloo.region.model.Region;
import com.igloo.region.response.RegionAdapter;

@Component
public class ClientAdapter {

	@Autowired
	private CountryAdapter countryAdapter;
	@Autowired
	private RegionAdapter regionAdapter;
	@Autowired
	private CityAdapter cityAdapter;
	@Autowired
	private CategoryAdapter categoryAdapter;

	public ClientResponse of(Client client) {
		if (client == null)
			return null;

		ClientResponse response = new ClientResponse();

		response.setId(client.getId());
		response.setIdNumber(client.getIdNumber());
		response.setFirstName(client.getFirstName());
		response.setLastName(client.getLastName());
		response.setStreetLine1(client.getStreetLine1());
		response.setStreetLine2(client.getStreetLine2());
		response.setPhoneNumber1(client.getPhoneNumber1());
		response.setPhoneNumber2(client.getPhoneNumber2());
		response.setEmail(client.getEmail());
		response.setFavorite(client.getFavorite());
		response.setWeb(client.getWeb());
		response.setProfilePic(client.getProfilePic());
		response.setZipCode(client.getZipCode());

		Country country = client.getCountry();
		response.setCountry(countryAdapter.of(country));

		Region region = client.getRegion();
		response.setRegion(regionAdapter.of(region));

		City city = client.getCity();
		response.setCity(cityAdapter.of(city));

		Category category = client.getCategory();
		response.setCategory(categoryAdapter.of(category));

		return response;
	}

	public List<ClientResponse> of(List<Client> clients) {

		List<ClientResponse> responses = new ArrayList<>();
		for (Client client : clients) {
			responses.add(of(client));
		}
		return responses;
	}

}
