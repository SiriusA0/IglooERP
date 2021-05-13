package com.igloo.status.response;

import java.util.LinkedList;
import java.util.List;

import com.igloo.invoice.model.Invoice;
import com.igloo.order.model.Order;
import com.igloo.order.response.OrderResponse;


public class StatusResponse {
	
    private int id;
	private String name;
    //private List<OrderResponse> orders;
   // private List<Invoice> invoices = new LinkedList<Invoice>();

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


	
	
}
