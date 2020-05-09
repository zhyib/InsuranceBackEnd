package com.insurance.repository;

import com.insurance.model.AutomobilePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  AutomobilePaymentRepository extends JpaRepository<AutomobilePayment, Integer> {
}
