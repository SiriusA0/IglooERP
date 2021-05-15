package com.igloo.payment.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igloo.payment.model.Payment;
import com.igloo.payment.response.PaymentResponse;
import com.igloo.payment.services.PaymentRepository;
import com.igloo.payment.services.PaymentService;
import com.igloo.sector.response.SectorResponse;
import com.igloo.status.response.StatusResponse;


@Controller
public class PaymentController {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	PaymentService paymentService;
	
//	@GetMapping("/api/payment/add")
//    @ResponseBody
//    public List<PaymentResponse> add_API(@RequestParam String name) {
//		paymentService.createPayment(name);
//        return paymentService.getAll();
//    }
	
	@GetMapping("api/payment/add")
	@ResponseBody
	public List<PaymentResponse> add_API(@RequestParam(required = false) String id,@RequestParam String name){
	    	
			
			
	    	if(id == null) {
	    		
	    		paymentService.createPayment(name);
	    	} else {
	    		int idInt=Integer.valueOf(id);
	    		paymentService.editPayment(idInt, name);	
	    	}
	
	
	return paymentService.getAll();
	}
	

	@GetMapping("/api/payment/get")
	@ResponseBody
	public List<PaymentResponse> getAll() {
        return paymentService.getAll();
    }
	
	
	@GetMapping("/api/payment/delete")
	@ResponseBody
	public List<PaymentResponse> delete_API(@RequestParam String id) {
		
		Boolean correct= paymentService.delete(id);
		List<PaymentResponse> payments;
		  if(correct) {
			  payments = paymentService.getAll();
		  }else {
			  payments = new LinkedList<PaymentResponse>();
		  }  
	  
	  return payments;
	}
	
	
	
}
