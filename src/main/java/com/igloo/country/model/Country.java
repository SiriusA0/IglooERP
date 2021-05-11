package com.igloo.country.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.igloo.city.model.City;
import com.igloo.region.model.Region;

@Entity
@Table(name="countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="country", fetch = FetchType.LAZY)
    private List<Region> regions = new LinkedList<Region>();
    
    
    
	public Country() {
		
	}

	public Country(String name, List<Region> regions) {
		this.name = name;
		this.regions = regions;
	}

	public Country(int id, String name, List<Region> regions) {
		this.id = id;
		this.name = name;
		this.regions = regions;
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

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
    
}

