package com.igloo.payment.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igloo.payment.model.Payment;


@Repository
public interface PaymentRepository  extends JpaRepository<Payment, Integer> { 

}
