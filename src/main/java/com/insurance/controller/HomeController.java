package com.insurance.controller;

import com.insurance.model.*;
import com.insurance.service.HomeInsuranceCustomerService;
import com.insurance.service.HomeService;
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
public class HomeController {
    private final HomeService homeService;
    private final HomeInsuranceCustomerService homeInsuranceCustomerService;

    @Autowired
    public HomeController(HomeService homeService, HomeInsuranceCustomerService homeInsuranceCustomerService) {
        this.homeService = homeService;
        this.homeInsuranceCustomerService = homeInsuranceCustomerService;
    }

    @RequestMapping("/home/getAll")
    public Result<Home> getAll() {
        return ResultReturn.success(homeService.getAll());
    }

    @RequestMapping("/home/add")
    public Result homeAdd(@RequestParam("homeId") int homeId,
                          @RequestParam("purchaseDate") String purchaseDate, @RequestParam("purchaseValue") double purchaseValue, @RequestParam("area") double area,
                          @RequestParam("type") char type, @RequestParam("autoFireNotification") int autoFireNotification, @RequestParam("homeSecuritySystem") int homeSecuritySystem,
                          @RequestParam("swimmingPool") char swimmingPool,
                          @RequestParam("basement") int basement, @RequestParam("hiId") int hiId) throws InterruptedException {
        Home e = homeService.findByHomeId(homeId);
        if (e != null)
            return ResultReturn.error(2, "that Home already exist");
        else {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date sd = formatter.parse(purchaseDate);
                e = saveHome(homeId, sd, purchaseValue, area, type, autoFireNotification, homeSecuritySystem, swimmingPool, basement, hiId);
            } catch (ParseException excpt) {
                excpt.printStackTrace();
            }

            return ResultReturn.success(homeService.save(e));
        }
    }

    @RequestMapping("/home/update/{homeId}")
    public Result homeUpdate(@PathVariable("homeId") int homeId,
                                   @RequestParam("purchaseDate") String purchaseDate, @RequestParam("purchaseValue") double purchaseValue, @RequestParam("area") double area,
                                   @RequestParam("type") char type, @RequestParam("autoFireNotification") int autoFireNotification, @RequestParam("homeSecuritySystem") int homeSecuritySystem,
                                   @RequestParam("swimmingPool") char swimmingPool,
                                   @RequestParam("basement") int basement, @RequestParam("hiId") int hiId) throws InterruptedException {
        Home e = homeService.findByHomeId(homeId);
        if (e == null) {
            return ResultReturn.error(1, "that homeId did not exist");
        } else {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date sd = formatter.parse(purchaseDate);
                e = saveHome(homeId, sd, purchaseValue, area, type, autoFireNotification, homeSecuritySystem, swimmingPool, basement, hiId);
            } catch (ParseException excpt) {
                excpt.printStackTrace();
            }
            return ResultReturn.success(homeService.save(e));
        }

    }

    @RequestMapping("home/search/{customerId}")
    public Result<Home> homeSearch(@PathVariable("customerId") int customerId) {
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
            Iterable<Home> k = homeService.getAll();
            ArrayList<Home> homeList = new ArrayList<Home>();
            for (Home p: k) {
                if (myList.contains(p.getHiId())) {
                    homeList.add(p);
                }
            }
            return ResultReturn.success(homeList);
        }
    }

    public Home saveHome(int homeId, Date purchaseDate, double purchaseValue, double area, char type,
                               int autoFireNotification, int homeSecuritySystem, char swimmingPool, int basement, int hiId) {
        Home e = new Home();
        e.setHomeId(homeId);
        e.setPurchaseDate(purchaseDate);
        e.setPurchaseValue(purchaseValue);
        e.setArea(area);
        e.setType(type);
        e.setAutoFireNotification(autoFireNotification);
        e.setHomeSecuritySystem(homeSecuritySystem);
        e.setSwimmingPool(swimmingPool);
        e.setBasement(basement);
        e.setHiId(hiId);
        return e;
    }
}
