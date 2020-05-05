package com.insurance.repository;

import com.insurance.model.HomeInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeInvoiceRepository extends JpaRepository<HomeInvoice, Integer> {
}
