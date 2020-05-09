package com.insurance.controller;

import com.insurance.model.HomeInsuranceCustomer;
import com.insurance.model.HomeInsuranceCustomer;
import com.insurance.model.Result;
import com.insurance.service.HomeInsuranceCustomerService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HomeInsuranceCustomerController {
    private final HomeInsuranceCustomerService homeInsuranceCustomerService;

    @Autowired
    public HomeInsuranceCustomerController(HomeInsuranceCustomerService homeInsuranceCustomerService) {
        this.homeInsuranceCustomerService = homeInsuranceCustomerService;
    }

    @RequestMapping("/hiCustomer/getAll")
    public Result<HomeInsuranceCustomer> getAll() {
        return ResultReturn.success(homeInsuranceCustomerService.getAll());
    }

    @RequestMapping("/hiCustomer/add")
    public Result homeInsuranceCustomerAdd(@RequestParam("customerId") int customerId, @RequestParam("hiId") int aiId,
                                                 @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @RequestParam("premiumAmount") double premiumAmount,
                                                 @RequestParam("status") char status) {
        HomeInsuranceCustomer e = homeInsuranceCustomerService.findByHomeInsuranceCustomerId(customerId);
        if (e != null)
            return ResultReturn.error(2, "that Home Insurance Customer already exist");
        else {
            e = saveHomeInsuranceCustomer(customerId, aiId, startDate, endDate, premiumAmount, status);
            return ResultReturn.success(homeInsuranceCustomerService.save(e));
        }
    }

    @RequestMapping("/hiCustomer/update/{customerId}")
    public Result homeInsuranceCustomerUpdate(@PathVariable("customerId") int customerId, @RequestParam("hiId") int aiId,
                                                    @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @RequestParam("premiumAmount") double premiumAmount,
                                                    @RequestParam("status") char status) {
        HomeInsuranceCustomer e = homeInsuranceCustomerService.findByHomeInsuranceCustomerId(customerId);
        if (e == null) {
            return ResultReturn.error(1, "that customerId did not exist");
        } else {
            e = saveHomeInsuranceCustomer(customerId, aiId, startDate, endDate, premiumAmount, status);
            return ResultReturn.success(homeInsuranceCustomerService.save(e));
        }

    }

    public HomeInsuranceCustomer saveHomeInsuranceCustomer(int customerId, int hiId, Date startDate, Date endDate, double premiumAmount, char status) {
        HomeInsuranceCustomer e = new HomeInsuranceCustomer();
        e.setCustomerId(customerId);
        e.setHiId(hiId);
        e.setStartDate(startDate);
        e.setEndDate(endDate);
        e.setPremiumAmount(premiumAmount);
        e.setStatus(status);
        return e;
    }
}
