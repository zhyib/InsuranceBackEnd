package com.insurance.controller;

import com.insurance.model.Driver;
import com.insurance.model.Result;
import com.insurance.service.DriverService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {
    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @RequestMapping("/driver/getAll")
    public Result<Driver> getAll() {
        return ResultReturn.success(driverService.getAll());
    }
}

