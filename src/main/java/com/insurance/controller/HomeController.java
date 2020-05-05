package com.insurance.controller;

import com.insurance.model.Home;
import com.insurance.model.Result;
import com.insurance.service.HomeService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @RequestMapping("/home/getAll")
    public Result<Home> getAll() {
        return ResultReturn.success(homeService.getAll());
    }
}
