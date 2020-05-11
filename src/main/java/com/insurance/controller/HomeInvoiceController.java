package com.insurance.controller;

import com.insurance.model.HomeInsuranceCustomer;
//import com.insurance.model.homeInsuranceCustomer;
//import com.insurance.model.homeInvoice;
import com.insurance.model.HomeInvoice;
import com.insurance.model.Result;
import com.insurance.service.HomeInsuranceCustomerService;
import com.insurance.service.HomeInvoiceService;
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
public class HomeInvoiceController {
    private final HomeInvoiceService homeInvoiceService;
    private final HomeInsuranceCustomerService homeInsuranceCustomerService;

    @Autowired
    public HomeInvoiceController(HomeInvoiceService homeInvoiceService, HomeInsuranceCustomerService homeInsuranceCustomerService) {
        this.homeInvoiceService = homeInvoiceService;
        this.homeInsuranceCustomerService = homeInsuranceCustomerService;
    }

    @RequestMapping("/homeInvoice/getAll")
    public Result<HomeInvoice> getAll() {
        return ResultReturn.success(homeInvoiceService.getAll());
    }

    @RequestMapping("/homeInvoice/add")
    public Result homeInvoiceAdd(@RequestParam("invoiceId") int invoiceId,
                                       @RequestParam("date") String date, @RequestParam("paymentDueDate") String paymentDueDate, @RequestParam("invoiceAmount") double invoiceAmount,
                                       @RequestParam("hiId") int hiId) throws InterruptedException {
        HomeInvoice e = homeInvoiceService.findByHomeInvoiceId(invoiceId);
        if (e != null)
            return ResultReturn.error(2, "that home Invoice already exist");
        else {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date sd = formatter.parse(date);
                Date ed = formatter.parse(paymentDueDate);
                e = saveHomeInvoice(invoiceId, sd, ed, invoiceAmount, hiId);
            } catch (ParseException excpt) {
                excpt.printStackTrace();
            }

            return ResultReturn.success(homeInvoiceService.save(e));
        }
    }

    @RequestMapping("/homeInvoice/update/{invoiceId}")
    public Result homeInvoiceUpdate(@PathVariable("invoiceId") int invoiceId,
                                          @RequestParam("date") String date, @RequestParam("paymentDueDate") String paymentDueDate, @RequestParam("invoiceAmount") double invoiceAmount,
                                          @RequestParam("hiId") int hiId) throws InterruptedException {
        HomeInvoice e = homeInvoiceService.findByHomeInvoiceId(invoiceId);
        if (e == null) {
            return ResultReturn.error(1, "that invoiceId did not exist");
        } else {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date sd = formatter.parse(date);
                Date ed = formatter.parse(paymentDueDate);
                e = saveHomeInvoice(invoiceId, sd, ed, invoiceAmount, hiId);
            } catch (ParseException excpt) {
                excpt.printStackTrace();
            }
            return ResultReturn.success(homeInvoiceService.save(e));
        }

    }

    @RequestMapping("/homeInvoice/search/{customerId}")
    public Result homeInvoiceSearch(@PathVariable("customerId") int customerId) {
        Iterable<HomeInsuranceCustomer> e = homeInsuranceCustomerService.getAll();
        ArrayList<Integer> myList = new ArrayList<Integer>();
        for (HomeInsuranceCustomer f: e) {
            if (f.getCustomerId()==customerId) {
                myList.add(f.getHiId());
            }
        }
        if (myList.isEmpty()) {
            return ResultReturn.error(1, "that customerId did not have home insurance");
        } else {
            Iterable<HomeInvoice> k = homeInvoiceService.getAll();
            ArrayList<HomeInvoice> homeInvoiceList = new ArrayList<HomeInvoice>();
            for (HomeInvoice p: k) {
                if (myList.contains(p.getHiId())) {
                    homeInvoiceList.add(p);
                }
            }
            return ResultReturn.success(homeInvoiceList);
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

