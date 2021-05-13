package com.igloo.country.response;

import java.util.LinkedList;
import java.util.List;

import com.igloo.region.model.Region;

public class CountryResponse {
	
	
	
	
    private int id;
    private String name;
    
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
    
    //private List<Region> regions = new LinkedList<Region>();

}
