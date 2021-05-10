package com.igloo.sector.model;

import com.igloo.invoice.model.Invoice;
import com.igloo.order.model.Order;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "sectors")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    //TODO
    @OneToMany(mappedBy = "sectors"/**, fetch = FetchType.EAGER**/)
    private List<Order> orders = new LinkedList<Order>();

    public Sector() {
    }

    public Sector(String name, List<Order> orders) {
        this.name = name;
        this.orders = orders;

    }

    public Sector(int id, String name, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.orders = orders;

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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


}
