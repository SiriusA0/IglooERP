package com.igloo.payment.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igloo.payment.model.Payment;
import com.igloo.payment.response.PaymentAdapter;
import com.igloo.payment.response.PaymentResponse;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	PaymentAdapter paymentAdapter;

	public Payment create(String name) {

		Payment payment = new Payment();
		payment.setName(name);
		paymentRepository.save(payment);
		return payment;
	}
	
	public void edit(Integer id, String name) {

		Payment payment = paymentRepository.findById(id).get();
		payment.setName(name);
		paymentRepository.save(payment);
	}

	public List<PaymentResponse> search() {

		List<Payment> payments = paymentRepository.findAll();
		return paymentAdapter.of(payments);
	}

	

	public Boolean delete(String id) {
		Boolean correct = false;
		try {
			int idToDelete = Integer.valueOf(id);
			paymentRepository.deleteById(idToDelete);
			correct = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return correct;
	}
}