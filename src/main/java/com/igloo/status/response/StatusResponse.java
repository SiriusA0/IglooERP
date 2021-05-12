package com.igloo.status.response;

import java.util.LinkedList;
import java.util.List;

import com.igloo.invoice.model.Invoice;
import com.igloo.order.model.Order;


public class StatusResponse {
	
    private int id;
	private String name;
    private List<Order> orders = new LinkedList<Order>();
    private List<Invoice> invoices = new LinkedList<Invoice>();

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
