package com.igloo.order.response;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.igloo.agent.model.Agent;
import com.igloo.client.model.Client;
import com.igloo.sector.model.Sector;
import com.igloo.status.model.Status;

public class OrderResponse {
	
	private int id;
    private Date creationDate;
    private Double totalAmount;
    private Client client;
    private Agent agent;
    private Status status;
    
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
	private Sector sector;

}
