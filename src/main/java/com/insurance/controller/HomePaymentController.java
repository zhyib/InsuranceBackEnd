package com.insurance.controller;

import com.insurance.model.Customer;
import com.insurance.model.HomePayment;
import com.insurance.model.Result;
import com.insurance.service.HomePaymentService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/homePayment/add")
    public Result homePaymentAdd(@RequestParam("paymentId") int paymentId, @RequestParam("paymentType") String paymentType,
                                 @RequestParam("invoiceId") int invoiceId) {
        HomePayment e = homePaymentService.findByHomePaymentId(paymentId);
        if (e != null)
            return ResultReturn.error(2, "that Home Payment already exist");
        else {
            e = saveHomePayment(paymentId, paymentType, invoiceId);
            return ResultReturn.success(homePaymentService.save(e));
        }
    }

    @RequestMapping("/homePayment/update/{paymentId}")
    public Result homePaymentUpdate(@PathVariable("paymentId") int paymentId, @RequestParam("paymentType") String paymentType,
                               @RequestParam("invoiceId") int invoiceId) {
        HomePayment e = homePaymentService.findByHomePaymentId(paymentId);
        if (e == null) {
            return ResultReturn.error(1, "that paymentId did not exist");
        } else {
            e = saveHomePayment(paymentId, paymentType, invoiceId);
            return ResultReturn.success(homePaymentService.save(e));
        }

    }

    @RequestMapping("/homePayment/delete/{paymentId}")
    public Result homePaymentDelete(@PathVariable("paymentId") int paymentId) {
        HomePayment e = homePaymentService.findByHomePaymentId(paymentId);
        if (e==null)
            return ResultReturn.error(1,"can't find this paymentId");
        homePaymentService.delete(e);
        return ResultReturn.success(e);
    }

    public HomePayment saveHomePayment(int paymentId, String paymentType, int invoiceId) {
        HomePayment e = new HomePayment();
        e.setPaymentId(paymentId);
        e.setPaymentType(paymentType);
        e.setInvoiceId(invoiceId);
        return e;
    }
}

