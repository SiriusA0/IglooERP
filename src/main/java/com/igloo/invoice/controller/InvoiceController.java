package com.igloo.invoice.controller;

import java.util.Date;
import java.util.List;

import com.igloo.payment.response.PaymentResponse;
import com.igloo.payment.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igloo.agent.response.AgentResponse;
import com.igloo.client.response.ClientResponse;
import com.igloo.client.service.ClientService;
import com.igloo.invoice.model.Invoice;
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

        List<InvoiceResponse> invoices = invoiceService.search(null, null, null);//TODO unificar nombres
        List<StatusResponse> statuses = statusService.getAll();//TODO unificar nombres
        List<PaymentResponse> paymentStatuses = paymentService.getAll();//TODO unificar nombres
        List<SectorResponse> sectors = sectorService.showSector();//TODO unificar nombres
        List<ClientResponse> clients = clientService.get();//TODO unificar nombres

        model.addAttribute("invoices", invoices);
        model.addAttribute("statuses", statuses);
        model.addAttribute("sectors", sectors);
        model.addAttribute("clients", clients);
        model.addAttribute("paymentStatuses", paymentStatuses);

        return "invoice/invoicelist";
    }


    @GetMapping("/api/invoice/add")
    @ResponseBody
    public List<InvoiceResponse> add_API(@RequestParam(required = false) Integer id, @RequestParam Integer clientId, /*@RequestParam Date dueDate,*/
                                         @RequestParam double preTax, @RequestParam Integer statusId, @RequestParam Integer paymentStatusId,
                                         @RequestParam Integer sectorId) {

        if (id == null) {
            invoiceService.createInvoice(clientId, null /*dueDate*//*TODO*/, preTax, statusId, paymentStatusId, sectorId);
        } else {

            invoiceService.editInvoice(id, clientId, preTax, statusId, paymentStatusId, sectorId);
        }


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


        return invoiceService.findInvoice(id);
    }


    @GetMapping("api/invoice/get")
    @ResponseBody
    public List<InvoiceResponse> buscador(@RequestParam(required = false) String action,
                                          @RequestParam(required = false) String option,
                                          @RequestParam(required = false) String term) {


        List<InvoiceResponse> invoices = invoiceService.search(action, option, term);

        return invoices;
    }
}

   