package com.igloo.order.response;

import com.igloo.agent.response.AgentResponse;

import com.igloo.client.response.ClientResponse;
import com.igloo.sector.response.SectorResponse;
import com.igloo.status.response.StatusResponse;

public class OrderResponse {

	private int id;
	private String creationDate;
	private Double totalAmount;
	private ClientResponse client;
	private AgentResponse agent;
	private StatusResponse status;
	private SectorResponse sector;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public ClientResponse getClient() {
		return client;
	}

	public void setClient(ClientResponse client) {
		this.client = client;
	}

	public AgentResponse getAgent() {
		return agent;
	}

	public void setAgent(AgentResponse agent) {
		this.agent = agent;
	}

	public StatusResponse getStatus() {
		return status;
	}

	public void setStatus(StatusResponse status) {
		this.status = status;
	}

	public SectorResponse getSector() {
		return sector;
	}

	public void setSector(SectorResponse sector) {
		this.sector = sector;
	}

}
