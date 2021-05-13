package com.igloo.region.response;


import java.util.LinkedList;
import java.util.List;


import com.igloo.city.response.CityResponse;
import com.igloo.country.response.CountryResponse;

public class RegionResponse {
	
	  
	    private int id;
	    private String name;
	    private List<CityResponse> cities = new LinkedList<>();
	    private CountryResponse country;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<CityResponse> getCities() {
			return cities;
		}
		public void setCities(List<CityResponse> cities) {
			this.cities = cities;
		}
		public CountryResponse getCountry() {
			return country;
		}
		public void setCountry(CountryResponse country) {
			this.country = country;
		}

}
