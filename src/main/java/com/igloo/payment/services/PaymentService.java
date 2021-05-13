package com.igloo.payment.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igloo.payment.model.Payment;
import com.igloo.payment.response.PaymentAdapter;
import com.igloo.payment.response.PaymentResponse;
import com.igloo.status.model.Status;
import com.igloo.status.response.StatusResponse;


@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	PaymentAdapter  paymentAdapter;
	
	public Payment createPayment(String name) {
		
		Payment payment = new Payment();
		payment.setName(name);
		paymentRepository.save(payment);
		return payment;
	}

	public List<PaymentResponse> getAll() {
		
		List<Payment> payments = paymentRepository.findAll();
		return paymentAdapter.of(payments);
	}
		
	public void delete(String id) {
		String idArray[] = id.split(",");
        for (String i : idArray){
            int idToDelete = Integer.valueOf(i);
            paymentRepository.deleteById(idToDelete);
        }
	}
}