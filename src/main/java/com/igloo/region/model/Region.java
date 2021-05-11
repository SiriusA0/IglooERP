package com.igloo.region.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.igloo.city.model.City;
import com.igloo.client.model.Client;
import com.igloo.country.model.Country;
import com.igloo.invoice.model.Invoice;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

	@JsonIgnore
    @OneToMany(mappedBy="region", fetch = FetchType.LAZY)
    private List<City> cities = new LinkedList<City>();

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;
    
    
    
	public Region() {
	
	}

	public Region(String name, List<City> cities, Country country) {
		this.name = name;
		this.cities = cities;
		this.country = country;
	}

	public Region(int id, String name, List<City> cities, Country country) {
		this.id = id;
		this.name = name;
		this.cities = cities;
		this.country = country;
	}

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

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
 
}
