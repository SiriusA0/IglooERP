package com.igloo.invoice.response;

import java.util.ArrayList;
import java.util.List;

import com.igloo.status.model.Status;
import com.igloo.status.response.StatusAdapter;
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
    @Autowired
    StatusAdapter statusAdapter;

    public InvoiceResponse of(Invoice invoice) {
        InvoiceResponse response = new InvoiceResponse();

        response.setId(invoice.getId());
        Client client = invoice.getClient();
        response.setClient(clientAdapter.of(client));
        
        
       response.setCreationDate(invoice.getCreationDate().toString().split(" ")[0]);
       response.setDueDate(invoice.getDueDate().toString().split(" ")[0]);
        
        double preTax = Math.round(invoice.getPreTax()*100.0)/100.0;
        response.setPreTax(preTax);
        
        double afterTax = Math.round(invoice.getAfterTax()*100.0)/100.0;
        response.setAfterTax(afterTax);
       
       
        Status status = invoice.getStatus();
        response.setStatus(statusAdapter.of(status));
        Payment payment = invoice.getPayment();
        response.setPayment(paymentAdapter.of(payment));
        Sector sector = invoice.getSector();
        response.setSector(sectorAdapter.of(sector));

        return response;
    }

    public List<InvoiceResponse> of(List<Invoice> invoices) {

        List<InvoiceResponse> responses = new ArrayList<>();
        for (Invoice invoice : invoices) {
            responses.add(of(invoice));
        }
        return responses;
    }
}
