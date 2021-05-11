package com.igloo.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igloo.payment.model.Payment;
import com.igloo.payment.services.PaymentRepository;
import com.igloo.payment.services.PaymentService;


@Controller
public class PaymentController {
	
	@Autowired
	PaymentRepository paymentrepository;
	
	@Autowired
	PaymentService paymentservice;
	
	@GetMapping("/api/payment/add")
    @ResponseBody
    public Payment add_API(@RequestParam String name) {

        return paymentservice.createPayment(name);
    }
}
