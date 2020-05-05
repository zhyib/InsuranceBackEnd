package com.insurance.controller;

import com.insurance.model.AutomobilePayment;
import com.insurance.model.Result;
import com.insurance.service.AutomobilePaymentService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutomobilePaymentController {
    private final AutomobilePaymentService automobilePaymentService;

    @Autowired
    public AutomobilePaymentController(AutomobilePaymentService automobilePaymentService) {
        this.automobilePaymentService = automobilePaymentService;
    }

    @RequestMapping("/automobilePayment/getAll")
    public Result<AutomobilePayment> getAll() {
        return ResultReturn.success(automobilePaymentService.getAll());
    }
}

