package com.insurance.controller;


import com.insurance.model.Automobile;
import com.insurance.model.Result;
import com.insurance.service.AutomobileService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutomobileController {
    private AutomobileService automobileService;

    @Autowired
    public AutomobileController(AutomobileService automobileService) {
        this.automobileService = automobileService;
    }

    @RequestMapping("/automoblie/getAll")
    public Result<Automobile> getAll() {
        return ResultReturn.success(automobileService.getAll());
    }
}
