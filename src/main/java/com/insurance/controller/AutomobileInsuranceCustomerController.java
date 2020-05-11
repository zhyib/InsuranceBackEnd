package com.insurance.controller;

import com.insurance.model.AutomobileInsuranceCustomer;
import com.insurance.model.AutomobilePayment;
import com.insurance.model.Customer;
import com.insurance.model.Result;
import com.insurance.service.AutomobileInsuranceCustomerService;
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
public class AutomobileInsuranceCustomerController {
    private final AutomobileInsuranceCustomerService automobileService;

    @Autowired
    public AutomobileInsuranceCustomerController(AutomobileInsuranceCustomerService automobileService) {
        this.automobileService = automobileService;
    }

    @RequestMapping("/aiCustomer/getAll")
    public Result<AutomobileInsuranceCustomer> getAll() {
        return ResultReturn.success(automobileService.getAll());
    }

    @RequestMapping("/aiCustomer/add")
    public Result automobileInsuranceCustomerAdd(@RequestParam("customerId") int customerId, @RequestParam("aiId") int aiId,
                                       @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("premiumAmount") double premiumAmount,
                                                 @RequestParam("status") char status) throws InterruptedException {
        AutomobileInsuranceCustomer e = automobileService.findByAutomobileInsuranceCustomerId(customerId);
        if (e != null)
            return ResultReturn.error(2, "that Automobile Insurance Customer already exist");
        else {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date sd = formatter.parse(startDate);
                Date ed = formatter.parse(endDate);
                e = saveAutomobileInsuranceCustomer(customerId, aiId, sd, ed, premiumAmount, status);
            } catch (ParseException excpt) {
                excpt.printStackTrace();
            }

            return ResultReturn.success(automobileService.save(e));
        }
    }

    @RequestMapping("/aiCustomer/update/{customerId}")
    public Result automobileInsuranceCustomerUpdate(@PathVariable("customerId") int customerId, @RequestParam("aiId") int aiId,
                         @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("premiumAmount") double premiumAmount,
                         @RequestParam("status") char status) throws InterruptedException {
        AutomobileInsuranceCustomer e = automobileService.findByAutomobileInsuranceCustomerId(customerId);
        if (e == null) {
            return ResultReturn.error(1, "that customerId did not exist");
        } else {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date sd = formatter.parse(startDate);
                Date ed = formatter.parse(endDate);
                e = saveAutomobileInsuranceCustomer(customerId, aiId, sd, ed, premiumAmount, status);
            } catch (ParseException excpt) {
                excpt.printStackTrace();
            }
            return ResultReturn.success(automobileService.save(e));
        }

    }

    @RequestMapping("/aiCustomer/search/{customerId}")
    public Result automobileInsuranceCustomerSearch(@PathVariable("customerId") int customerId) {
        Iterable<AutomobileInsuranceCustomer> e = automobileService.getAll();
        for (AutomobileInsuranceCustomer f: e) {
            if (f.getCustomerId() == customerId) {
                return ResultReturn.success(f);
            }
        }
        return ResultReturn.error(1,"can't find this customerId");
    }

    public AutomobileInsuranceCustomer saveAutomobileInsuranceCustomer(int customerId, int aiId, Date startDate, Date endDate, double premiumAmount, char status) {
        AutomobileInsuranceCustomer e = new AutomobileInsuranceCustomer();
        e.setCustomerId(customerId);
        e.setAiId(aiId);
        e.setStartDate(startDate);
        e.setEndDate(endDate);
        e.setPremiumAmount(premiumAmount);
        e.setStatus(status);
        return e;
    }
}