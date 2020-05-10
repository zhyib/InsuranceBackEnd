package com.insurance.controller;

import com.insurance.model.Driver;
import com.insurance.model.Home;
import com.insurance.model.Result;
import com.insurance.service.DriverService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;


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

