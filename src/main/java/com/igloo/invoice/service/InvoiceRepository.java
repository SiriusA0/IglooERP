package com.igloo.invoice.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igloo.invoice.model.Invoice;



@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}


