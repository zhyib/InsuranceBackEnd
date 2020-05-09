package com.insurance.service;

import com.insurance.exception.ExceptionType;
import com.insurance.exception.InsuranceException;
import com.insurance.model.AutomobileInsuranceCustomer;
import com.insurance.model.AutomobilePayment;
import com.insurance.repository.AutomobileInsuranceCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutomobileInsuranceCustomerService {
    @Autowired
    AutomobileInsuranceCustomerRepository automobileRepository;

    public Iterable<AutomobileInsuranceCustomer> getAll() {
        return automobileRepository.findAll();
    }

    public AutomobileInsuranceCustomer save(AutomobileInsuranceCustomer r) {
        return automobileRepository.save(r);
    }

    public AutomobileInsuranceCustomer findByAutomobileInsuranceCustomer(int automobileno) throws InsuranceException {
        AutomobileInsuranceCustomer automobile = automobileRepository.findById(automobileno).orElse(null);

        if (automobile != null) {
            return automobile;
        } else {
            throw new InsuranceException(ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getCode(),
                    ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getMsg());
        }
    }

    public AutomobileInsuranceCustomer findByAutomobileInsuranceCustomerId(int automobileInsuranceCustomerId) {
        return automobileRepository.findById(automobileInsuranceCustomerId).orElse(null);

    }

    public void delete(AutomobileInsuranceCustomer r) {
        automobileRepository.delete(r);
    }
}
