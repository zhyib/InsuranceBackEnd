package com.insurance.controller;

import com.insurance.model.HomeInvoice;
import com.insurance.model.Result;
import com.insurance.service.HomeInvoiceService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    @RequestMapping("/homeInvoice/add")
    public Result homeInvoiceAdd(@RequestParam("invoiceId") int invoiceId,
                                       @RequestParam("date") Date date, @RequestParam("paymentDueDate") Date paymentDueDate, @RequestParam("invoiceAmount") double invoiceAmount,
                                       @RequestParam("hiId") int hiId) {
        HomeInvoice e = homeInvoiceService.findByHomeInvoiceId(invoiceId);
        if (e != null)
            return ResultReturn.error(2, "that home Invoice already exist");
        else {
            e = saveHomeInvoice(invoiceId, date, paymentDueDate, invoiceAmount, hiId);
            return ResultReturn.success(homeInvoiceService.save(e));
        }
    }

    @RequestMapping("/homeInvoice/update/{invoiceId}")
    public Result homeInvoiceUpdate(@PathVariable("invoiceId") int invoiceId,
                                          @RequestParam("date") Date date, @RequestParam("paymentDueDate") Date paymentDueDate, @RequestParam("invoiceAmount") double invoiceAmount,
                                          @RequestParam("hiId") int hiId) {
        HomeInvoice e = homeInvoiceService.findByHomeInvoiceId(invoiceId);
        if (e == null) {
            return ResultReturn.error(1, "that invoiceId did not exist");
        } else {
            e = saveHomeInvoice(invoiceId, date, paymentDueDate, invoiceAmount, hiId);
            return ResultReturn.success(homeInvoiceService.save(e));
        }

    }

    public HomeInvoice saveHomeInvoice(int invoiceId, Date date, Date paymentDueDate, double invoiceAmount, int hiId) {
        HomeInvoice e = new HomeInvoice();
        e.setInvoiceId(invoiceId);
        e.setDate(date);
        e.setPaymentDueDate(paymentDueDate);
        e.setInvoiceAmount(invoiceAmount);
        e.setHiId(hiId);
        return e;
    }
}

