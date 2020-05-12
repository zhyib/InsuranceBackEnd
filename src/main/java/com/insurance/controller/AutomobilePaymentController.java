package com.insurance.controller;

import com.insurance.model.*;
import com.insurance.service.AutomobileInsuranceCustomerService;
import com.insurance.service.AutomobileInvoiceService;
import com.insurance.service.AutomobilePaymentService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AutomobilePaymentController {
    private final AutomobilePaymentService automobilePaymentService;
    private final AutomobileInsuranceCustomerService automobileInsuranceCustomerService;
    private final AutomobileInvoiceService automobileInvoiceService;

    @Autowired
    public AutomobilePaymentController(AutomobilePaymentService automobilePaymentService, AutomobileInsuranceCustomerService automobileInsuranceCustomerService, AutomobileInvoiceService automobileInvoiceService) {
        this.automobilePaymentService = automobilePaymentService;
        this.automobileInsuranceCustomerService = automobileInsuranceCustomerService;
        this.automobileInvoiceService = automobileInvoiceService;
    }

    @RequestMapping("/automobilePayment/getAll")
    public Result<AutomobilePayment> getAll() { return ResultReturn.success(automobilePaymentService.getAll()); }

    @RequestMapping("/automobilePayment/add")
    public Result automobilePaymentAdd(@RequestParam("paymentId") int paymentId, @RequestParam("paymentType") String paymentType,
                                 @RequestParam("invoiceId") int invoiceId) {
        AutomobilePayment e = automobilePaymentService.findByAutomobilePaymentId(paymentId);
        if (e != null)
            return ResultReturn.error(2, "that Automobile Payment already exist");
        else {
            e = saveAutomobilePayment(paymentId, paymentType, invoiceId);
            return ResultReturn.success(automobilePaymentService.save(e));
        }
    }

    @RequestMapping("/automobilePayment/update/{paymentId}")
    public Result automobilePaymentUpdate(@PathVariable("paymentId") int paymentId, @RequestParam("paymentType") String paymentType,
                               @RequestParam("invoiceId") int invoiceId) {
        AutomobilePayment e = automobilePaymentService.findByAutomobilePaymentId(paymentId);
        if (e == null) {
            return ResultReturn.error(1, "that paymentId did not exist");
        } else {
            e = saveAutomobilePayment(paymentId, paymentType, invoiceId);
            return ResultReturn.success(automobilePaymentService.save(e));
        }

    }

    @RequestMapping("/automobilePayment/delete/{paymentId}")
    public Result automobilePaymentDelete(@PathVariable("paymentId") int paymentId) {
        AutomobilePayment e = automobilePaymentService.findByAutomobilePaymentId(paymentId);
        if (e==null)
            return ResultReturn.error(1,"can't find this paymentId");
        automobilePaymentService.delete(e);
        return ResultReturn.success(e);
    }

    @RequestMapping("/automobilePayment/search/{customerId}")
    public Result automobilePaymentSearch(@PathVariable("customerId") int customerId) {
        Iterable<AutomobileInsuranceCustomer> e = automobileInsuranceCustomerService.getAll();
        ArrayList<Integer> myList = new ArrayList<Integer>();
        for (AutomobileInsuranceCustomer f: e) {
            if (f.getCustomerId()==customerId) {
                myList.add(f.getAiId());
            }
        }
        if (myList.isEmpty()) {
            return ResultReturn.error(1, "that customer did not have auto insurance");
        } else {
            Iterable<AutomobileInvoice> k = automobileInvoiceService.getAll();
            ArrayList<Integer> automobileInvoiceList = new ArrayList<Integer>();
            for (AutomobileInvoice p: k) {
                if (myList.contains(p.getAiId())) {
                    automobileInvoiceList.add(p.getInvoiceId());
                }
            }
            if (automobileInvoiceList.isEmpty()) {
                return ResultReturn.error(1, "that customer did not have auto insurance invoice");
            } else {
                Iterable<AutomobilePayment> autoPayment = automobilePaymentService.getAll();
                ArrayList<AutomobilePayment> paymentList = new ArrayList<AutomobilePayment>();
                for (AutomobilePayment o: autoPayment) {
                    if (automobileInvoiceList.contains(o.getInvoiceId())) {
                        paymentList.add(o);
                    }
                }
                if (paymentList.isEmpty()) {
                    return ResultReturn.error(1, "that customer did not have auto insurance payment");
                } else
                    return ResultReturn.success(paymentList);
            }
        }
    }

    public AutomobilePayment saveAutomobilePayment(int paymentId, String paymentType, int invoiceId) {
        AutomobilePayment e = new AutomobilePayment();
        e.setPaymentId(paymentId);
        e.setPaymentType(paymentType);
        e.setInvoiceId(invoiceId);
        return e;
    }
}

