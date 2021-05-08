package com.igloo.invoice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.igloo.client.model.Client;
import com.igloo.payment.model.Payment;
import com.igloo.status.model.Status;

@Entity
@Table(name="invoices")
public class Invoice {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    
    @Column(name = "creation_date", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date creationDate;
    
    @Column(name = "due_date", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dueDate;
    
    @Column(name = "pre_tax")
    private Double preTax;
    
    @Column(name = "after_tax")
    private Double afterTax;
    
    @ManyToOne
    @JoinColumn(name="status_id")
    private Status status;
    
    @ManyToOne
    @JoinColumn(name="payment_status_id")
    private Payment payment;

	public Invoice(Client client, Date creationDate, Date dueDate, Double preTax, Double afterTax, Status status,
			Payment payment) {
		this.client = client;
		this.creationDate = creationDate;
		this.dueDate = dueDate;
		this.preTax = preTax;
		this.afterTax = afterTax;
		this.status = status;
		this.payment = payment;
	}

	public Invoice(int id, Client client, Date creationDate, Date dueDate, Double preTax, Double afterTax,
			Status status, Payment payment) {
		this.id = id;
		this.client = client;
		this.creationDate = creationDate;
		this.dueDate = dueDate;
		this.preTax = preTax;
		this.afterTax = afterTax;
		this.status = status;
		this.payment = payment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
    
    
}
