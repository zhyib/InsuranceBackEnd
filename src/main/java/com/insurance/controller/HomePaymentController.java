package com.insurance.controller;

import com.insurance.model.*;
import com.insurance.service.HomeInsuranceCustomerService;
import com.insurance.service.HomeInvoiceService;
import com.insurance.service.HomePaymentService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class HomePaymentController {
    private final HomePaymentService homePaymentService;
    private final HomeInsuranceCustomerService homeInsuranceCustomerService;
    private final HomeInvoiceService homeInvoiceService;

    @Autowired
    public HomePaymentController(HomePaymentService homePaymentService, HomeInsuranceCustomerService homeInsuranceCustomerService, HomeInvoiceService homeInvoiceService) {
        this.homePaymentService = homePaymentService;
        this.homeInsuranceCustomerService = homeInsuranceCustomerService;
        this.homeInvoiceService = homeInvoiceService;
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

    @RequestMapping("/homePayment/search/{customerId}")
    public Result homePaymentSearch(@PathVariable("customerId") int customerId) {
        Iterable<HomeInsuranceCustomer> e = homeInsuranceCustomerService.getAll();
        ArrayList<Integer> myList = new ArrayList<Integer>();
        for (HomeInsuranceCustomer f: e) {
            if (f.getCustomerId()==customerId) {
                myList.add(f.getHiId());
            }
        }

        if (myList.isEmpty()) {
            return ResultReturn.error(1, "that customer did not have home insurance");
        } else {
            Iterable<HomeInvoice> k = homeInvoiceService.getAll();
            ArrayList<Integer> homeInvoiceList = new ArrayList<Integer>();
            for (HomeInvoice p: k) {
                if (myList.contains(p.getHiId())) {
                    homeInvoiceList.add(p.getInvoiceId());
                }
            }
            if (homeInvoiceList.isEmpty()) {
                return ResultReturn.error(1, "that customer did not have home insurance invoice");
            } else {
                Iterable<HomePayment> homePayment = homePaymentService.getAll();
                ArrayList<HomePayment> paymentList = new ArrayList<HomePayment>();
                for (HomePayment o: homePayment) {
                    if (homeInvoiceList.contains(o.getInvoiceId())) {
                        paymentList.add(o);
                    }
                }
                if (paymentList.isEmpty()) {
                    return ResultReturn.error(1, "that customer did not have home insurance payment");
                } else
                    return ResultReturn.success(paymentList);
            }
        }
    }

    public HomePayment saveHomePayment(int paymentId, String paymentType, int invoiceId) {
        HomePayment e = new HomePayment();
        e.setPaymentId(paymentId);
        e.setPaymentType(paymentType);
        e.setInvoiceId(invoiceId);
        return e;
    }
}

