package com.insurance.repository;

import com.insurance.model.HomePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomePaymentRepository extends JpaRepository<HomePayment, Integer> {
}
