package com.insurance.controller;

import com.insurance.model.HomeInsuranceCustomer;
import com.insurance.model.Result;
import com.insurance.service.HomeInsuranceCustomerService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeInsuranceCustomerController {
    private final HomeInsuranceCustomerService homeInsuranceCustomerService;

    @Autowired
    public HomeInsuranceCustomerController(HomeInsuranceCustomerService homeInsuranceCustomerService) {
        this.homeInsuranceCustomerService = homeInsuranceCustomerService;
    }

    @RequestMapping("/hiCustomer/getAll")
    public Result<HomeInsuranceCustomer> getAll() {
        return ResultReturn.success(homeInsuranceCustomerService.getAll());
    }
}
