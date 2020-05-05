package com.insurance.controller;

import com.insurance.model.Customer;
import com.insurance.model.Result;
import com.insurance.service.CustomerService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customer/getAll")
    public Result<Customer> getAll() {
        return ResultReturn.success(customerService.getAll());
    }
}

