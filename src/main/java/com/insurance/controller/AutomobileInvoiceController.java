package com.insurance.controller;

import com.insurance.model.Automobile;
import com.insurance.model.AutomobileInvoice;
import com.insurance.model.HomeInsuranceCustomer;
import com.insurance.model.Result;
import com.insurance.service.AutomobileInvoiceService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

