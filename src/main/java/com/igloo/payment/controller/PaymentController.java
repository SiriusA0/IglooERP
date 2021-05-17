package com.igloo.payment.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igloo.payment.response.PaymentResponse;
import com.igloo.payment.services.PaymentRepository;
import com.igloo.payment.services.PaymentService;

@Controller
public class PaymentController {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	PaymentService paymentService;

	@GetMapping("api/payment/add")
	@ResponseBody
	public List<PaymentResponse> add_API(@RequestParam(required = false) String id, @RequestParam String name) {

		if (id == null) {

			paymentService.create(name);
		} else {
			int idInt = Integer.valueOf(id);
			paymentService.edit(idInt, name);
		}

		return paymentService.search();
	}

	@GetMapping("/api/payment/get")
	@ResponseBody
	public List<PaymentResponse> search_API() {
		return paymentService.search();
	}

	@GetMapping("/api/payment/delete")
	@ResponseBody
	public List<PaymentResponse> delete_API(@RequestParam String id) {

		Boolean correct = paymentService.delete(id);
		List<PaymentResponse> payments;
		if (correct) {
			payments = paymentService.search();
		} else {
			payments = new LinkedList<PaymentResponse>();
		}

		return payments;
	}

}
