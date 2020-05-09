package com.insurance.service;

import com.insurance.exception.ExceptionType;
import com.insurance.exception.InsuranceException;
import com.insurance.model.AutomobileInsuranceCustomer;
import com.insurance.model.HomeInsuranceCustomer;
import com.insurance.repository.HomeInsuranceCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeInsuranceCustomerService {
    @Autowired
    HomeInsuranceCustomerRepository homeInsuranceCustomerRepository;

    public Iterable<HomeInsuranceCustomer> getAll() {
        return homeInsuranceCustomerRepository.findAll();
    }

    public HomeInsuranceCustomer save(HomeInsuranceCustomer r) {
        return homeInsuranceCustomerRepository.save(r);
    }

    public HomeInsuranceCustomer findByHomeInsuranceCustomer(int homeInsuranceCustomerno) throws InsuranceException {
        HomeInsuranceCustomer homeInsuranceCustomer = homeInsuranceCustomerRepository.findById(homeInsuranceCustomerno).orElse(null);

        if (homeInsuranceCustomer != null) {
            return homeInsuranceCustomer;
        } else {
            throw new InsuranceException(ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getCode(),
                    ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getMsg());
        }
    }

    public HomeInsuranceCustomer findByHomeInsuranceCustomerId(int homeInsuranceCustomerId) {
        return homeInsuranceCustomerRepository.findById(homeInsuranceCustomerId).orElse(null);

    }

    public void delete(HomeInsuranceCustomer r) {
        homeInsuranceCustomerRepository.delete(r);
    }
}
