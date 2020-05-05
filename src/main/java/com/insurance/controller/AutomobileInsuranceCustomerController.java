package com.insurance.controller;

import com.insurance.model.AutomobileInsuranceCustomer;
import com.insurance.model.Result;
import com.insurance.service.AutomobileInsuranceCustomerService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutomobileInsuranceCustomerController {
    private final AutomobileInsuranceCustomerService automobileService;

    @Autowired
    public AutomobileInsuranceCustomerController(AutomobileInsuranceCustomerService automobileService) {
        this.automobileService = automobileService;
    }

    @RequestMapping("/aiCustomer/getAll")
    public Result<AutomobileInsuranceCustomer> getAll() {
        return ResultReturn.success(automobileService.getAll());
    }
}