package com.igloo.invoice.controller;

import java.util.List;

import com.igloo.payment.response.PaymentResponse;
import com.igloo.payment.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igloo.client.response.ClientResponse;
import com.igloo.client.service.ClientService;
import com.igloo.invoice.response.InvoiceResponse;
import com.igloo.invoice.service.InvoiceRepository;
import com.igloo.invoice.service.InvoiceService;

import com.igloo.sector.response.SectorResponse;
import com.igloo.sector.service.SectorService;
import com.igloo.status.response.StatusResponse;
import com.igloo.status.service.StatusService;

@Controller
public class InvoiceController {

	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	StatusService statusService;
	@Autowired
	SectorService sectorService;
	@Autowired
	ClientService clientService;
	@Autowired
	PaymentService paymentService;

	@GetMapping("/invoice")
	public String readInvoice(Model model) {

		List<InvoiceResponse> invoices = invoiceService.search(null, null, null, 1);
		List<StatusResponse> statuses = statusService.search();
		List<PaymentResponse> paymentStatuses = paymentService.search();
		List<SectorResponse> sectors = sectorService.search();
		List<ClientResponse> clients = clientService.get();

		model.addAttribute("invoices", invoices);
		model.addAttribute("statuses", statuses);
		model.addAttribute("sectors", sectors);
		model.addAttribute("clients", clients);
		model.addAttribute("paymentStatuses", paymentStatuses);

		return "invoice/invoicelist";
	}

	@GetMapping("/api/invoice/add")
	@ResponseBody
	public List<InvoiceResponse> add_API(@RequestParam(required = false) Integer id,
			@RequestParam Integer clientId, /* @RequestParam Date dueDate, */
			@RequestParam double preTax, @RequestParam Integer statusId, @RequestParam Integer paymentStatusId,
			@RequestParam Integer sectorId, @RequestParam(required = false) String action,
			@RequestParam(required = false) String option, @RequestParam(required = false) String term,
			@RequestParam(required = false) Integer page) {

		if (id == null) {
			invoiceService.create(clientId, null, preTax, statusId, paymentStatusId, sectorId);
		} else {
			invoiceService.edit(id, clientId, preTax, statusId, paymentStatusId, sectorId);
		}
		return invoiceService.search(action, option, term, page);
	}

	@GetMapping("api/invoice/find")
	@ResponseBody
	public InvoiceResponse find_API(@RequestParam Integer id) {
		return invoiceService.find(id);
	}

	@GetMapping("api/invoice/get")
	@ResponseBody
	public List<InvoiceResponse> search_API(@RequestParam(required = false) String action,
			@RequestParam(required = false) String option, @RequestParam(required = false) String term,
			@RequestParam(required = false) Integer page) {

		List<InvoiceResponse> invoices = invoiceService.search(action, option, term, page);

		return invoices;
	}

	@GetMapping("/api/invoice/delete")
	@ResponseBody
	public List<InvoiceResponse> delete_API(@RequestParam String id, @RequestParam(required = false) String action,
			@RequestParam(required = false) String option, @RequestParam(required = false) String term,
			@RequestParam(required = false) Integer page) {

		invoiceService.delete(id);

		List<InvoiceResponse> invoices = invoiceService.search(action, option, term, page);

		return invoices;
	}
}
