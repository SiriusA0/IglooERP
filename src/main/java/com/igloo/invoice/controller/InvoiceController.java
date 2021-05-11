package com.igloo.invoice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igloo.invoice.model.Invoice;
import com.igloo.invoice.service.InvoiceRepository;
import com.igloo.invoice.service.InvoiceService;

@Controller
public class InvoiceController {
	
	@Autowired
	InvoiceRepository invoicerepository;
	@Autowired
	InvoiceService invoiceservice;
	
	 @GetMapping("/api/invoice/add")
	 @ResponseBody
	 public Invoice add_API(@RequestParam Integer clientId,@RequestParam Date date,@RequestParam Date dueDate,
			 @RequestParam double preTax,@RequestParam double afterTax,@RequestParam Integer statusId,@RequestParam Integer paymentStatusId, 
			 @RequestParam Integer sectorId) {

	        return invoiceservice.createInvoice(clientId, date, dueDate, preTax, afterTax, statusId, paymentStatusId, sectorId);
	    }
}
