package com.igloo.payment.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.igloo.payment.model.Payment;

@Component
public class PaymentAdapter {

		
		public PaymentResponse of(Payment payment) {
			
			PaymentResponse response=new PaymentResponse();
			
			response.setId(response.getId());
			response.setName(response.getName());
			
			return response;
		}
		
		public List<PaymentResponse> of(List<Payment> payments) {
	        
	        List<PaymentResponse> responses = new ArrayList<>();
	        for(Payment payment : payments) {
	            responses.add(of(payment));
	        }
	        
	        return responses;      
	    

}}
