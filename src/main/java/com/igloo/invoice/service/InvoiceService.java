package com.igloo.invoice.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igloo.client.model.Client;
import com.igloo.client.service.ClientRepository;
import com.igloo.invoice.model.Invoice;
import com.igloo.payment.model.Payment;
import com.igloo.payment.services.PaymentRepository;
import com.igloo.sector.model.Sector;
import com.igloo.sector.service.SectorRepository;
import com.igloo.status.model.Status;
import com.igloo.status.service.StatusRepository;

@Service
public class InvoiceService {
	
	@Autowired
	InvoiceRepository invoicerepository;
	
	@Autowired
	ClientRepository clientrepository;
	@Autowired
	StatusRepository statusrepository;
	@Autowired
	PaymentRepository paymentrepository;
	@Autowired
	SectorRepository sectorrepository;
	
	public Invoice createInvoice(Integer clientId, Date date, Date dueDate, double preTax, double afterTax, Integer statusId,
			Integer paymentStatusId, Integer sectorId) {
		
		Invoice invoice = new Invoice();
		
		
		//////////////REPOSITORIES////////////////////////////////////////////////////
		Client client = clientrepository.findById(clientId).get();
		Status status = statusrepository.findById(statusId).get();
		Payment payment = paymentrepository.findById(paymentStatusId).get();
		Sector sector = sectorrepository.findById(sectorId).get();
		/////////////////////////////////////////////////////////////////////////////
		
		////////////////////DATES////////////////////////////////////////////////////
		Calendar calendar = Calendar.getInstance();
		Calendar due_calendar = new GregorianCalendar(2022,12,1);
        Date date_ =  calendar.getTime();
        Date due_date = due_calendar.getTime();
		/////////////////////////////////////////////////////////////////////////////
        
        
		invoice.setClient(client);
		invoice.setCreationDate(date_);
		invoice.setDueDate(due_date);
		invoice.setPreTax(preTax);
		invoice.setAfterTax(preTax*1.21);
		invoice.setStatus(status);
		invoice.setPayment(payment);
		invoice.setSector(sector);
		
		return invoice;
	}
}
