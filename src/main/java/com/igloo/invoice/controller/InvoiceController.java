package com.igloo.invoice.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igloo.invoice.model.Invoice;
import com.igloo.invoice.response.InvoiceResponse;
import com.igloo.invoice.service.InvoiceRepository;
import com.igloo.invoice.service.InvoiceService;
import com.igloo.order.response.OrderResponse;

@Controller
public class InvoiceController {
	
	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	InvoiceService invoiceService;
	
	 @GetMapping("/api/invoice/add")
	 @ResponseBody
	 public List<InvoiceResponse> add_API(@RequestParam Integer clientId,@RequestParam Date dueDate,
			 @RequestParam double preTax,@RequestParam double afterTax,@RequestParam Integer statusId,@RequestParam Integer paymentStatusId, 
			 @RequestParam Integer sectorId) {
		 	invoiceService.createInvoice(clientId, dueDate, preTax, afterTax, statusId, paymentStatusId, sectorId);
		 	return invoiceService.getAll(); 
	    }
	    
	    @GetMapping("/api/invoice/delete")
	    @ResponseBody
	    public List<InvoiceResponse> delete_API(@RequestParam String id) {

	    	invoiceService.deleteOrder(id);
	        return invoiceService.getAll();
	    }
	    
	    @GetMapping("api/invoice/find")
	    @ResponseBody
	    public InvoiceResponse find_API(@RequestParam Integer id) {
	    	
	    	
	    	return invoiceService.editOrder(id);
	    }
}
