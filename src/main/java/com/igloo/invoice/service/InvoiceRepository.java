package com.igloo.invoice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igloo.invoice.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	List<Invoice> findByClientFirstNameContainingOrClientLastNameContaining(String term, String term2,
			Pageable pageable);

}
