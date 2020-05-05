package com.insurance.controller;

import com.insurance.model.AutomobileInvoice;
import com.insurance.model.Result;
import com.insurance.service.AutomobileInvoiceService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutomobileInvoiceController {
    private final AutomobileInvoiceService automobileInvoiceService;

    @Autowired
    public AutomobileInvoiceController(AutomobileInvoiceService automobileInvoiceService) {
        this.automobileInvoiceService = automobileInvoiceService;
    }

    @RequestMapping("/automobileInvoice/getAll")
    public Result<AutomobileInvoice> getAll() {
        return ResultReturn.success(automobileInvoiceService.getAll());
    }
}

