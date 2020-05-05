package com.insurance.repository;

import com.insurance.model.HomeInsuranceCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeInsuranceCustomerRepository extends JpaRepository<HomeInsuranceCustomer, Integer> {
}
