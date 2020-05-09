package com.insurance.controller;

import com.insurance.model.Customer;
import com.insurance.model.Result;
import com.insurance.service.CustomerService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customer/getAll")
    public Result<Customer> getAll() {
        return ResultReturn.success(customerService.getAll());
    }

    @RequestMapping("/customer/add")
    public Result customerAdd(@RequestParam("customerId") int customerId, @RequestParam("lastName") String lastName,
                              @RequestParam("firstName") String firstName, @RequestParam("address") String address, @RequestParam("state") String state,
                              @RequestParam("city") String city, @RequestParam("zipcode") String zipcode, @RequestParam("gender") char gender,
                              @RequestParam("martial") char martial, @RequestParam("customerType") char customerType ) {
        Customer e = customerService.findByCustomerId(customerId);
        if (e != null)
            return ResultReturn.error(2, "that customer already exist");
        else {
            System.out.println(customerId);
            e = saveCustomer(customerId, lastName, firstName, address, state,
                    city, zipcode, gender, martial, customerType);
            return ResultReturn.success(customerService.save(e));
        }
    }

    @RequestMapping("/customer/update/{customerId}")
    public Result customerUpdate(@PathVariable("customerId") int customerId, @RequestParam("lastName") String lastName,
                               @RequestParam("firstName") String firstName, @RequestParam("address") String address, @RequestParam("state") String state,
                               @RequestParam("city") String city, @RequestParam("zipcode") String zipcode, @RequestParam("gender") char gender,
                               @RequestParam("martial") char martial, @RequestParam("customerType") char customerType ) {
        Customer e = customerService.findByCustomerId(customerId);
        if (e == null) {
            return ResultReturn.error(1, "that customerId did not exist");
        } else {
            e = saveCustomer(customerId, lastName, firstName, address, state,
                    city, zipcode, gender, martial, customerType);
            return ResultReturn.success(customerService.save(e));
        }

    }

    @RequestMapping("/customer/delete/{customerId}")
    public Result customerDelete(@PathVariable("customerId") int customerId) {
        Customer e = customerService.findByCustomerId(customerId);
        if (e==null)
            return ResultReturn.error(1,"can't find this customerId");
        customerService.delete(e);
        return ResultReturn.success(e);
    }



    public Customer saveCustomer(int customerId, String lastName, String firstName, String address, String state,
                                 String city, String zipcode, char gender, char martial, char customerType) {
        Customer e = new Customer();
        e.setCustomerId(customerId);
        e.setLastName(lastName);
        e.setFirstName(firstName);
        e.setAddress(address);
        e.setState(state);
        e.setCity(city);
        e.setZipcode(zipcode);
        e.setGender(gender);
        e.setMartial(martial);
        e.setCustomerType(customerType);
        return e;
    }




}

