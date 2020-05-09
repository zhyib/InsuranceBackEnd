package com.insurance.controller;

import com.insurance.model.AutomobilePayment;
import com.insurance.model.Result;
import com.insurance.service.AutomobilePaymentService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutomobilePaymentController {
    private final AutomobilePaymentService automobilePaymentService;

    @Autowired
    public AutomobilePaymentController(AutomobilePaymentService automobilePaymentService) {
        this.automobilePaymentService = automobilePaymentService;
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

    @RequestMapping("/AutomobilePayment/update/{paymentId}")
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

    public AutomobilePayment saveAutomobilePayment(int paymentId, String paymentType, int invoiceId) {
        AutomobilePayment e = new AutomobilePayment();
        e.setPaymentId(paymentId);
        e.setPaymentType(paymentType);
        e.setInvoiceId(invoiceId);
        return e;
    }
}

