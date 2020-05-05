package com.insurance.service;

import com.insurance.exception.ExceptionType;
import com.insurance.exception.InsuranceException;
import com.insurance.model.AutomobilePayment;
import com.insurance.repository.AutomobilePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutomobilePaymentService {
    @Autowired
    AutomobilePaymentRepository automobilePaymentRepository;

    public Iterable<AutomobilePayment> getAll() {
        return automobilePaymentRepository.findAll();
    }

    public AutomobilePayment save(AutomobilePayment r) {
        return automobilePaymentRepository.save(r);
    }

    public AutomobilePayment findByAutomobilePayment(int automobilePaymentno) throws InsuranceException {
        AutomobilePayment automobilePayment = automobilePaymentRepository.findById(automobilePaymentno).orElse(null);

        if (automobilePayment != null) {
            return automobilePayment;
        } else {
            throw new InsuranceException(ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getCode(),
                    ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getMsg());
        }
    }

    public void delete(AutomobilePayment r) {
        automobilePaymentRepository.delete(r);
    }
}
