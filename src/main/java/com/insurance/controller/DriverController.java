package com.insurance.controller;

import com.insurance.model.*;
import com.insurance.service.AutomobileInsuranceCustomerService;
import com.insurance.service.AutomobileService;
import com.insurance.service.DriverService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;


@RestController
public class DriverController {
    private final DriverService driverService;
    private final AutomobileService automobileService;
    private final AutomobileInsuranceCustomerService automobileInsuranceCustomerService;


    @Autowired
    public DriverController(DriverService driverService, AutomobileService automobileService, AutomobileInsuranceCustomerService automobileInsuranceCustomerService) {
        this.driverService = driverService;
        this.automobileService = automobileService;
        this.automobileInsuranceCustomerService = automobileInsuranceCustomerService;
    }

    @RequestMapping("/driver/getAll")
    public Result<Driver> getAll() {
        return ResultReturn.success(driverService.getAll());
    }

    @RequestMapping("/driver/add")
    public Result driverAdd(@RequestParam("driverId") int driverId,
                            @RequestParam("licenseNo") String licenseNo, @RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName,
                            @RequestParam("birthdate") String birthdate, @RequestParam("automobileId") int automobileId) throws InterruptedException{
        Driver e = driverService.findByDriverId(driverId);
        if (e != null)
            return ResultReturn.error(2, "that Home already exist");
        else {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date bd = formatter.parse(birthdate);
                e = saveDriver(driverId, licenseNo, lastName, firstName, bd, automobileId);
            } catch (ParseException excpt) {
                 excpt.printStackTrace();
            }


            return ResultReturn.success(driverService.save(e));
        }
    }

    @RequestMapping("/driver/update/{driverId}")
    public Result driverUpdate(@PathVariable("driverId") int driverId,
                             @RequestParam("licenseNo") String licenseNo, @RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName,
                             @RequestParam("birthdate") String birthdate, @RequestParam("automobileId") int automobileId) throws InterruptedException {
        Driver e = driverService.findByDriverId(driverId);
        if (e == null) {
            return ResultReturn.error(1, "that driverId did not exist");
        } else {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date bd = formatter.parse(birthdate);
                e = saveDriver(driverId, licenseNo, lastName, firstName, bd, automobileId);
            } catch (ParseException excpt) {
                excpt.printStackTrace();
            }
            return ResultReturn.success(driverService.save(e));
        }

    }

    @RequestMapping("/driver/delete/{driverId}")
    public Result driverDelete(@PathVariable("driverId") int driverId) {
        Driver e = driverService.findByDriverId(driverId);
        if (e==null)
            return ResultReturn.error(1,"can't find this driverId");
        driverService.delete(e);
        return ResultReturn.success(e);
    }

    @RequestMapping("driver/search/{customerId}")
    public Result<Driver> driverSearch(@PathVariable("customerId") int customerId) {
        Iterable<AutomobileInsuranceCustomer> q = automobileInsuranceCustomerService.getAll();
        ArrayList<Integer> aiCustomerList = new ArrayList<Integer>();
        for (AutomobileInsuranceCustomer f: q) {
            if (f.getCustomerId()==customerId) {
                aiCustomerList.add(f.getAiId());
            }
        }
        if (aiCustomerList.isEmpty()) {
            return ResultReturn.error(1, "that customerId did not exist");
        } else {
            Iterable<Automobile> e = automobileService.getAll();
            ArrayList<Integer> automobileList = new ArrayList<Integer>();
            for (Automobile f: e){
                if (aiCustomerList.contains(f.getAiId())) {
                    automobileList.add(f.getAutomobileId());
                }
            }
            if (automobileList.isEmpty()) {
                return ResultReturn.error(1, "that customer did not have an automobile");
            } else
            {
                Iterable<Driver> g = driverService.getAll();
                ArrayList<Driver> driverList = new ArrayList<Driver>();
                for (Driver k: g) {
                    if (automobileList.contains(k.getAutomobileId())) {
                        driverList.add(k);
                    }
                }
                return ResultReturn.success(driverList);
            }
        }




    }

    public Driver saveDriver(int driverId, String licenseNo, String lastName, String firstName, Date birthdate, int automobileId) {
        Driver e = new Driver();
        e.setDriverId(driverId);
        e.setLicenseNo(licenseNo);
        e.setLastName(lastName);
        e.setFirstName(firstName);
        e.setBirthdate(birthdate);
        e.setAutomobileId(automobileId);
        return e;
    }
}

