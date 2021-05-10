package com.igloo.order.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.igloo.sector.model.Sector;
import org.springframework.format.annotation.DateTimeFormat;

import com.igloo.agent.model.Agent;
import com.igloo.client.model.Client;
import com.igloo.status.model.Status;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "creation_date", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date creationDate;

    @Column(name = "total_amount")
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    public Order(Date creationDate, Double totalAmount, Client client, Agent agent, Status status, Sector sector) {
        this.creationDate = creationDate;
        this.totalAmount = totalAmount;
        this.client = client;
        this.agent = agent;
        this.status = status;
        this.sector = sector;
    }

    public Order(int id, Date creationDate, Double totalAmount, Client client, Agent agent, Status status, Sector sector) {
        this.id = id;
        this.creationDate = creationDate;
        this.totalAmount = totalAmount;
        this.client = client;
        this.agent = agent;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
}
