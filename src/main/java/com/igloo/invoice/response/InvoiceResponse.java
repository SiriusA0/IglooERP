package com.igloo.invoice.response;

import com.igloo.client.response.ClientResponse;
import com.igloo.payment.response.PaymentResponse;
import com.igloo.sector.response.SectorResponse;
import com.igloo.status.response.StatusResponse;

public class InvoiceResponse {

	private int id;
	private ClientResponse client;
	private String creationDate;
	private String dueDate;
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

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
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
