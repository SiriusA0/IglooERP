package com.igloo.payment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igloo.payment.model.Payment;


@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentrepository;
	
	public Payment createPayment(String name) {
		
		Payment payment = new Payment();
		payment.setName(name);
		
		paymentrepository.save(payment);
		
		
		return payment;
		
	}
}
