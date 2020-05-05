package com.insurance.repository;

import com.insurance.model.Automobile;
import com.insurance.model.AutomobileInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomobileInvoiceRepository extends JpaRepository<AutomobileInvoice, Integer> {
}
