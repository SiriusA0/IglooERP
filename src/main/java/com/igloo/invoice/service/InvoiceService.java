package com.igloo.invoice.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.igloo.agent.model.Agent;
import com.igloo.client.model.Client;
import com.igloo.client.service.ClientRepository;
import com.igloo.invoice.model.Invoice;
import com.igloo.invoice.response.InvoiceAdapter;
import com.igloo.invoice.response.InvoiceResponse;
import com.igloo.order.model.Order;
import com.igloo.order.response.OrderResponse;
import com.igloo.payment.model.Payment;
import com.igloo.payment.services.PaymentRepository;
import com.igloo.sector.model.Sector;
import com.igloo.sector.service.SectorRepository;
import com.igloo.status.model.Status;
import com.igloo.status.service.StatusRepository;

@Service
public class InvoiceService {
	
	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	StatusRepository statusRepository;
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	SectorRepository sectorRepository;
	@Autowired
	private InvoiceAdapter invoiceAdapter;
	
	public List<InvoiceResponse> createInvoice(Integer clientId, Date dueDate, double preTax, Integer statusId,
			Integer paymentId, Integer sectorId) {
		
		////////////////////DATES////////////////////////////////////////////////////
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date = calendar.getTime();
		
		Calendar due_calendar = Calendar.getInstance();
        due_calendar.set(calendar.get(Calendar.YEAR)+1, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        due_calendar.set(Calendar.HOUR_OF_DAY, 0);
        due_calendar.set(Calendar.MINUTE, 0);
        due_calendar.set(Calendar.SECOND, 0);
        due_calendar.set(Calendar.MILLISECOND, 0);
        Date due_date = due_calendar.getTime();
		/////////////////////////////////////////////////////////////////////////////
		
		List<Invoice> invoices = new LinkedList<>();
		Invoice invoice = new Invoice();
		
		invoice.setClient(clientRepository.findById(clientId).get());
		invoice.setCreationDate(date);
		invoice.setDueDate(due_date);
		invoice.setPreTax(preTax);
		invoice.setAfterTax(preTax*1.21);
		invoice.setStatus(statusRepository.findById(statusId).get());
		invoice.setPayment(paymentRepository.findById(paymentId).get());
		invoice.setSector(sectorRepository.findById(sectorId).get());
		
		invoiceRepository.save(invoice);
		invoices.add(invoice);
		

    	return invoiceAdapter.of(invoices);
	}
	
public List<InvoiceResponse> search(String action, String option, String term){
    	
    	List<Invoice> invoices = null;
    	
    	if(action == null || action.isEmpty()) {
			Pageable pageable = PageRequest.of(0, 25);
			Page<Invoice> lista = invoiceRepository.findAll(pageable);
			invoices = lista.getContent();
			
		} else if(action.equals("sort")) {
			Sort.Direction direction = Sort.Direction.fromString(option);
			Sort sort = Sort.by(direction,term);
			Pageable pageable = PageRequest.of(0, 25, sort);
    		invoices = invoiceRepository.findAll(pageable).getContent();
    		
    	}else if(action.equals("search")) {
    		if(option.equals("client")) {
				Pageable pageable = PageRequest.of(0, 25);
    			invoices = invoiceRepository.findByClientFirstNameContainingOrClientLastNameContaining(term, term, pageable);    			
       		}
  
    	}
    	
    	System.out.println("Invoices: "+ invoices.size());
    	return invoiceAdapter.of(invoices);
    }

public void deleteOrder(String id) {
	String idArray[] = id.split(",");
    for (String i : idArray){
        int newId = Integer.valueOf(i);
        invoiceRepository.deleteById(newId);
    }
	
}

public List<InvoiceResponse> getAll() {
	
	return invoiceAdapter.of(invoiceRepository.findAll());
}

public InvoiceResponse findInvoice(Integer id) {
	Invoice invoice = new Invoice();
	invoice=invoiceRepository.findById(id).get();
	
	return invoiceAdapter.of(invoice);
}

public void editInvoice(Integer id, Integer clientId, double preTax, Integer statusId,
		Integer paymentStatusId, Integer sectorId) {
	
	Invoice invoice = invoiceRepository.findById(id).get();
	
	invoice.setClient(clientRepository.findById(clientId).get());
	invoice.setPreTax(preTax);
	invoice.setAfterTax(preTax*1.21);
	invoice.setStatus(statusRepository.findById(statusId).get());
	invoice.setPayment(paymentRepository.findById(paymentStatusId).get());
	invoice.setSector(sectorRepository.findById(sectorId).get());
	
	invoiceRepository.save(invoice);
	
}

}
