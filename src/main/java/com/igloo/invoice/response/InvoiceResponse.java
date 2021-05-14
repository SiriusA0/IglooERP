package com.igloo.invoice.response;

import java.util.Date;

import com.igloo.category.model.Category;
import com.igloo.city.model.City;
import com.igloo.client.model.Client;
import com.igloo.client.response.ClientResponse;
import com.igloo.country.model.Country;
import com.igloo.payment.model.Payment;
import com.igloo.payment.response.PaymentResponse;
import com.igloo.region.model.Region;
import com.igloo.sector.model.Sector;
import com.igloo.sector.response.SectorResponse;
import com.igloo.status.model.Status;
import com.igloo.status.response.StatusResponse;

public class InvoiceResponse {

    private int id;
    private ClientResponse client;
    private Date creationDate;
    private Date dueDate;
    private Double preTax;
    private Double afterTax;
    private StatusResponse status;
    private PaymentResponse payment;
    private SectorResponse sector;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientResponse getClient() {
        return client;
    }

    public void setClient(ClientResponse client) {
        this.client = client;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Double getPreTax() {
        return preTax;
    }

    public void setPreTax(Double preTax) {
        this.preTax = preTax;
    }

    public Double getAfterTax() {
        return afterTax;
    }

    public void setAfterTax(Double afterTax) {
        this.afterTax = afterTax;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public PaymentResponse getPayment() {
        return payment;
    }

    public void setPayment(PaymentResponse payment) {
        this.payment = payment;
    }

    public SectorResponse getSector() {
        return sector;
    }

    public void setSector(SectorResponse sector) {
        this.sector = sector;
    }


}
