package com.igloo.order.response;

import java.util.Date;

import com.igloo.agent.response.AgentResponse;
import org.springframework.format.annotation.DateTimeFormat;

import com.igloo.agent.model.Agent;
import com.igloo.client.model.Client;
import com.igloo.client.response.ClientResponse;
import com.igloo.sector.model.Sector;
import com.igloo.sector.response.SectorResponse;
import com.igloo.status.model.Status;
import com.igloo.status.response.StatusResponse;

public class OrderResponse {

    private int id;
    private Date creationDate;
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
