package com.igloo.payment.controller;

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
import com.igloo.status.response.StatusResponse;


@Controller
public class PaymentController {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	PaymentService paymentService;
	
	@GetMapping("/api/payment/add")
    @ResponseBody
    public List<PaymentResponse> add_API(@RequestParam String name) {
		paymentService.createPayment(name);
        return paymentService.getAll();
    }
	
	@GetMapping("/api/payment/get")
	@ResponseBody
	public List<PaymentResponse> getAll() {
        return paymentService.getAll();
    }
	
	
	
	
	
}
