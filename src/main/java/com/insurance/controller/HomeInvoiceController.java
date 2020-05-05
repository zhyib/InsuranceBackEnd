package com.insurance.controller;

import com.insurance.model.HomeInvoice;
import com.insurance.model.Result;
import com.insurance.service.HomeInvoiceService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeInvoiceController {
    private final HomeInvoiceService homeInvoiceService;

    @Autowired
    public HomeInvoiceController(HomeInvoiceService homeInvoiceService) {
        this.homeInvoiceService = homeInvoiceService;
    }

    @RequestMapping("/homeInvoice/getAll")
    public Result<HomeInvoice> getAll() {
        return ResultReturn.success(homeInvoiceService.getAll());
    }
}

