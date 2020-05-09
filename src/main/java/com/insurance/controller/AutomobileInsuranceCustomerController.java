package com.insurance.controller;

import com.insurance.model.AutomobileInsuranceCustomer;
import com.insurance.model.AutomobilePayment;
import com.insurance.model.Result;
import com.insurance.service.AutomobileInsuranceCustomerService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
                                       @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @RequestParam("premiumAmount") double premiumAmount,
                                                 @RequestParam("status") char status) {
        AutomobileInsuranceCustomer e = automobileService.findByAutomobileInsuranceCustomerId(customerId);
        if (e != null)
            return ResultReturn.error(2, "that Automobile Insurance Customer already exist");
        else {
            e = saveAutomobileInsuranceCustomer(customerId, aiId, startDate, endDate, premiumAmount, status);
            return ResultReturn.success(automobileService.save(e));
        }
    }

    @RequestMapping("/aiCustomer/update/{customerId}")
    public Result automobileInsuranceCustomerUpdate(@PathVariable("customerId") int customerId, @RequestParam("aiId") int aiId,
                         @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @RequestParam("premiumAmount") double premiumAmount,
                         @RequestParam("status") char status) {
        AutomobileInsuranceCustomer e = automobileService.findByAutomobileInsuranceCustomerId(customerId);
        if (e == null) {
            return ResultReturn.error(1, "that customerId did not exist");
        } else {
            e = saveAutomobileInsuranceCustomer(customerId, aiId, startDate, endDate, premiumAmount, status);
            return ResultReturn.success(automobileService.save(e));
        }

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