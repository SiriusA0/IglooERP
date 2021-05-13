package com.igloo.invoice.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.igloo.client.model.Client;
import com.igloo.client.response.ClientAdapter;
import com.igloo.invoice.model.Invoice;
import com.igloo.payment.model.Payment;
import com.igloo.payment.response.PaymentAdapter;
import com.igloo.sector.model.Sector;
import com.igloo.sector.response.SectorAdapter;
@Component
public class InvoiceAdapter {
	
	@Autowired 
	ClientAdapter clientAdapter;
	@Autowired 
	PaymentAdapter paymentAdapter;
	@Autowired 
	SectorAdapter sectorAdapter;
	
	public InvoiceResponse of(Invoice invoice) {
		InvoiceResponse response = new InvoiceResponse();
		
		response.setId(invoice.getId());
		Client client = invoice.getClient();
		response.setClient(clientAdapter.of(client));
		response.setCreationDate(invoice.getCreationDate());
		response.setDueDate(invoice.getDueDate());
		response.setPreTax(invoice.getPreTax());
		response.setAfterTax(invoice.getAfterTax());
		response.setStatus(invoice.getStatus());
		Payment payment = invoice.getPayment();
		response.setPayment(paymentAdapter.of(payment));
		Sector sector = invoice.getSector();
		response.setSector(sectorAdapter.of(sector));
		
		return response;
	}
	
public List<InvoiceResponse> of(List<Invoice> invoices) {
        
        List<InvoiceResponse> responses = new ArrayList<>();
        for(Invoice invoice : invoices) {
            responses.add(of(invoice));
        }
        return responses;      
    }
}
