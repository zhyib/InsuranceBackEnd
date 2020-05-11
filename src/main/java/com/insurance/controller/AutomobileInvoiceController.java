package com.insurance.controller;

import com.insurance.model.*;
import com.insurance.service.AutomobileInsuranceCustomerService;
import com.insurance.service.AutomobileInvoiceService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class AutomobileInvoiceController {
    private final AutomobileInvoiceService automobileInvoiceService;
    private final AutomobileInsuranceCustomerService automobileInsuranceCustomerService;

    @Autowired
    public AutomobileInvoiceController(AutomobileInvoiceService automobileInvoiceService, AutomobileInsuranceCustomerService automobileInsuranceCustomerService) {
        this.automobileInvoiceService = automobileInvoiceService;
        this.automobileInsuranceCustomerService = automobileInsuranceCustomerService;
    }

    @RequestMapping("/automobileInvoice/getAll")
    public Result<AutomobileInvoice> getAll() {
        return ResultReturn.success(automobileInvoiceService.getAll());
    }

    @RequestMapping("/automobileInvoice/add")
    public Result automobileInvoiceAdd(@RequestParam("invoiceId") int invoiceId,
                                           @RequestParam("date") String date, @RequestParam("paymentDueDate") String paymentDueDate, @RequestParam("invoiceAmount") double invoiceAmount,
                                           @RequestParam("aiId") int aiId) throws InterruptedException {
        AutomobileInvoice e = automobileInvoiceService.findByAutomobileInvoiceId(invoiceId);
        if (e != null)
            return ResultReturn.error(2, "that Automobile Invoice already exist");
        else {

            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date sd = formatter.parse(date);
                Date ed = formatter.parse(paymentDueDate);
                e = saveAutomobileInvoice(invoiceId, sd, ed, invoiceAmount, aiId);
            } catch (ParseException excpt) {
                excpt.printStackTrace();
            }

            return ResultReturn.success(automobileInvoiceService.save(e));
        }
    }

    @RequestMapping("/automobileInvoice/update/{invoiceId}")
    public Result automobileInvoiceUpdate(@PathVariable("invoiceId") int invoiceId,
                                          @RequestParam("date") String date, @RequestParam("paymentDueDate") String paymentDueDate, @RequestParam("invoiceAmount") double invoiceAmount,
                                          @RequestParam("aiId") int aiId) throws InterruptedException {
        AutomobileInvoice e = automobileInvoiceService.findByAutomobileInvoiceId(invoiceId);
        if (e == null) {
            return ResultReturn.error(1, "that invoiceId did not exist");
        } else {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date sd = formatter.parse(date);
                Date ed = formatter.parse(paymentDueDate);
                e = saveAutomobileInvoice(invoiceId, sd, ed, invoiceAmount, aiId);
            } catch (ParseException excpt) {
                excpt.printStackTrace();
            }
            return ResultReturn.success(automobileInvoiceService.save(e));
        }

    }

    @RequestMapping("/automobileInvoice/search/{customerId}")
    public Result automobileInvoiceSearch(@PathVariable("customerId") int customerId) {
        Iterable<AutomobileInsuranceCustomer> e = automobileInsuranceCustomerService.getAll();
        ArrayList<Integer> myList = new ArrayList<Integer>();
        for (AutomobileInsuranceCustomer f: e) {
            if (f.getCustomerId()==customerId) {
                myList.add(f.getAiId());
            }
        }
        if (myList.isEmpty()) {
            return ResultReturn.error(1, "that customerId did not have auto insurance");
        } else {
            Iterable<AutomobileInvoice> k = automobileInvoiceService.getAll();
            ArrayList<AutomobileInvoice> automobileInvoiceList = new ArrayList<AutomobileInvoice>();
            for (AutomobileInvoice p: k) {
                if (myList.contains(p.getAiId())) {
                    automobileInvoiceList.add(p);
                }
            }
            return ResultReturn.success(automobileInvoiceList);
        }
    }



    public AutomobileInvoice saveAutomobileInvoice(int invoiceId, Date date, Date paymentDueDate, double invoiceAmount, int aiId) {
        AutomobileInvoice e = new AutomobileInvoice();
        e.setInvoiceId(invoiceId);
        e.setDate(date);
        e.setPaymentDueDate(paymentDueDate);
        e.setInvoiceAmount(invoiceAmount);
        e.setAiId(aiId);
        return e;
    }
}

