package com.insurance.controller;


import com.insurance.model.Automobile;
import com.insurance.model.AutomobileInvoice;
import com.insurance.model.Result;
import com.insurance.service.AutomobileService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class AutomobileController {
    private final AutomobileService automobileService;

    @Autowired
    public AutomobileController(AutomobileService automobileService) {
        this.automobileService = automobileService;
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
