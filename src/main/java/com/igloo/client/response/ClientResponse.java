package com.igloo.client.response;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.igloo.category.model.Category;
import com.igloo.category.response.CategoryResponse;
import com.igloo.city.model.City;
import com.igloo.city.response.CityResponse;
import com.igloo.country.model.Country;
import com.igloo.country.response.CountryResponse;
import com.igloo.region.model.Region;
import com.igloo.region.response.RegionResponse;

public class ClientResponse {

	private int id;
	private String idNumber;
	private String firstName;
	private String lastName;
	private char type;
	private String email;
	private String web;
	private Boolean favorite = false;

	private String streetLine1;
	private String streetLine2;
	private String phoneNumber1;
	private String phoneNumber2;
	private Integer zipCode;
	private String profilePic;
	private CountryResponse country;
	private RegionResponse region;
	private CityResponse city;
	private CategoryResponse category;
	private String popover;

	public String getPopover() {
		return popover;
	}

	public void setPopover(String popover) {
		this.popover = popover;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getFavorite() {
		return favorite;
	}

	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getStreetLine1() {
		return streetLine1;
	}

	public void setStreetLine1(String streetLine1) {
		this.streetLine1 = streetLine1;
	}

	public String getStreetLine2() {
		return streetLine2;
	}

	public void setStreetLine2(String streetLine2) {
		this.streetLine2 = streetLine2;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public CountryResponse getCountry() {
		return country;
	}

	public void setCountry(CountryResponse country) {
		this.country = country;
	}

	public RegionResponse getRegion() {
		return region;
	}

	public void setRegion(RegionResponse region) {
		this.region = region;
	}

	public CityResponse getCity() {
		return city;
	}

	public void setCity(CityResponse city) {
		this.city = city;
	}

	public CategoryResponse getCategory() {
		return category;
	}

	public void setCategory(CategoryResponse category) {
		this.category = category;
	}

}
