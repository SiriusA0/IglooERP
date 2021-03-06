package com.igloo.city.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.igloo.region.model.Region;

@Entity
@Table(name="cities")
public class City {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String name;
    
    @ManyToOne
    @JoinColumn(name="region_id")
    private Region region;
    
    

	public City() {
	}

	public City(String name, Region region) {
		this.name = name;
		this.region = region;
	}

	public City(int id, String name, Region region) {
		this.id = id;
		this.name = name;
		this.region = region;
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

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
    
    
}
