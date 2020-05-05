package com.insurance.service;

import com.insurance.exception.ExceptionType;
import com.insurance.exception.InsuranceException;
import com.insurance.model.Customer;
import com.insurance.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Iterable<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer save(Customer r) {
        return customerRepository.save(r);
    }

    public Customer findByCustomer(int customerno) throws InsuranceException {
        Customer customer = customerRepository.findById(customerno).orElse(null);

        if (customer != null) {
            return customer;
        } else {
            throw new InsuranceException(ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getCode(),
                    ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getMsg());
        }
    }

    public void delete(Customer r) {
        customerRepository.delete(r);
    }
}