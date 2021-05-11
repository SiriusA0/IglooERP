package com.igloo.payment.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.igloo.invoice.model.Invoice;

@Entity
@Table(name="payments_status")
public class Payment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
	private String name;
	
	@OneToMany(mappedBy="payment"/**, fetch = FetchType.EAGER**/)
    private List<Invoice> invoices = new LinkedList<Invoice>();
	
	
	

	public Payment() {
		
	}

	public Payment(String name, List<Invoice> invoices) {
		this.name = name;
		this.invoices = invoices;
	}

	public Payment(int id, String name, List<Invoice> invoices) {
		this.id = id;
		this.name = name;
		this.invoices = invoices;
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

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	
	
}
