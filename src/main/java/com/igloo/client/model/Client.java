package com.igloo.client.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import com.igloo.category.model.Category;
import com.igloo.city.model.City;
import com.igloo.country.model.Country;
import com.igloo.invoice.model.Invoice;
import com.igloo.order.model.Order;
import com.igloo.region.model.Region;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private char type;

    @Column
    private String email;

    @Column
    private String web;

    @Column(name = "street_line_1")
    private String streetLine1;

    @Column(name = "street_line_2")
    private String streetLine2;

    @Column(name = "phone_number_1")
    private String phoneNumber1;

    @Column(name = "phone_number_1")
    private String phoneNumber2;

    @Column
    private Integer zipCode;

    @Column(name = "profile_pic")
    private String profilePic;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "client"/**, fetch = FetchType.EAGER**/)
    private List<Invoice> invoices = new LinkedList<Invoice>();

    @OneToMany(mappedBy = "client"/**, fetch = FetchType.EAGER**/)
    private List<Order> orders = new LinkedList<Order>();

    public Client(int id, String idNumber, String firstName, String lastName, char type, String email, String web, String streetLine1, String streetLine2, String phoneNumber1, String phoneNumber2, Integer zipCode, String profilePic, Country country, Region region, City city, Category category, List<Invoice> invoices, List<Order> orders) {
        this.id = id;
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.email = email;
        this.web = web;
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.zipCode = zipCode;
        this.profilePic = profilePic;
        this.country = country;
        this.region = region;
        this.city = city;
        this.category = category;
        this.invoices = invoices;
        this.orders = orders;
    }

    public Client(String idNumber, String firstName, String lastName, char type, String email, String web, String streetLine1, String streetLine2, String phoneNumber1, String phoneNumber2, Integer zipCode, String profilePic, Country country, Region region, City city, Category category, List<Invoice> invoices, List<Order> orders) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.email = email;
        this.web = web;
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.zipCode = zipCode;
        this.profilePic = profilePic;
        this.country = country;
        this.region = region;
        this.city = city;
        this.category = category;
        this.invoices = invoices;
        this.orders = orders;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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
}
