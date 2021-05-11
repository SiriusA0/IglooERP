package com.igloo.status.model;

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
import com.igloo.order.model.Order;

@Entity
@Table(name="statuses")
public class Status {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
	private String name;
	
	//TODO
	@OneToMany(mappedBy="status"/**, fetch = FetchType.EAGER**/)
    private List<Order> orders = new LinkedList<Order>();
	
	//TODO
	@OneToMany(mappedBy="status"/**, fetch = FetchType.EAGER**/)
    private List<Invoice> invoices = new LinkedList<Invoice>();
	
	

	public Status() {
	}

	public Status(String name, List<Order> orders, List<Invoice> invoices) {
		this.name = name;
		this.orders = orders;
		this.invoices = invoices;
	}

	public Status(int id, String name, List<Order> orders, List<Invoice> invoices) {
		this.id = id;
		this.name = name;
		this.orders = orders;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	
}
