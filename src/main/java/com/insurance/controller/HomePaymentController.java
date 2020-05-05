package com.insurance.controller;

import com.insurance.model.HomePayment;
import com.insurance.model.Result;
import com.insurance.service.HomePaymentService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePaymentController {
    private final HomePaymentService homePaymentService;

    @Autowired
    public HomePaymentController(HomePaymentService homePaymentService) {
        this.homePaymentService = homePaymentService;
    }

    @RequestMapping("/homePayment/getAll")
    public Result<HomePayment> getAll() {
        return ResultReturn.success(homePaymentService.getAll());
    }
}

