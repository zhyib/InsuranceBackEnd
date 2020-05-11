package com.insurance.controller;


import com.insurance.model.Automobile;
import com.insurance.model.AutomobileInsuranceCustomer;
import com.insurance.model.Result;
import com.insurance.service.AutomobileInsuranceCustomerService;
import com.insurance.service.AutomobileService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.security.util.ByteArrayTagOrder;

import java.util.*;

@RestController
public class AutomobileController {
    private final AutomobileService automobileService;
    private final AutomobileInsuranceCustomerService automobileInsuranceCustomerService;

    @Autowired
    public AutomobileController(AutomobileService automobileService, AutomobileInsuranceCustomerService automobileInsuranceCustomerService) {
        this.automobileService = automobileService;
        this.automobileInsuranceCustomerService= automobileInsuranceCustomerService;
    }

    @RequestMapping("/automobile/getAll")
    public Result<Automobile> getAll() {
        return ResultReturn.success(automobileService.getAll());
    }

    @RequestMapping("/automobile/add")
    public Result automobileAdd(@RequestParam("automobileId") int automobileId,
                                       @RequestParam("vin") String vin, @RequestParam("makeModelYear") int makeModelYear, @RequestParam("status") char status,
                                       @RequestParam("aiId") int aiId) {
        Automobile e = automobileService.findByAutomobileId(automobileId);
        if (e != null)
            return ResultReturn.error(2, "that Automobile already exist");
        else {
            e = saveAutomobile(automobileId, vin, makeModelYear, status, aiId);
            return ResultReturn.success(automobileService.save(e));
        }
    }

    @RequestMapping("/automobile/update/{automobileId}")
    public Result automobileUpdate(@PathVariable("automobileId") int automobileId,
                                          @RequestParam("vin") String vin, @RequestParam("makeModelYear") int makeModelYear, @RequestParam("status") char status,
                                          @RequestParam("aiId") int aiId) {
        Automobile e = automobileService.findByAutomobileId(automobileId);
        if (e == null) {
            return ResultReturn.error(1, "that automobileId did not exist");
        } else {
            e = saveAutomobile(automobileId, vin, makeModelYear, status, aiId);
            return ResultReturn.success(automobileService.save(e));
        }

    }

    @RequestMapping("automobile/search/{customerId}")
    public Result<Automobile> automobileSearch(@PathVariable("customerId") int customerId) {
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
            Iterable<Automobile> k = automobileService.getAll();
            ArrayList<Automobile> automobileList = new ArrayList<Automobile>();
            for (Automobile p: k) {
                if (myList.contains(p.getAiId())) {
                    automobileList.add(p);
                }
            }
            return ResultReturn.success(automobileList);
        }
    }

    public Automobile saveAutomobile(int automobileId, String vin, int makeModelYear, char status, int aiId) {
        Automobile e = new Automobile();
        e.setAutomobileId(automobileId);
        e.setVin(vin);
        e.setMakeModelYear(makeModelYear);
        e.setStatus(status);
        e.setAiId(aiId);
        return e;
    }
}
