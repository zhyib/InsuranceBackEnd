package com.insurance.repository;

import com.insurance.model.AutomobileInsuranceCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomobileInsuranceCustomerRepository extends JpaRepository<AutomobileInsuranceCustomer, Integer> {
}
