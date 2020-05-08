package com.insurance.service;

import com.insurance.exception.ExceptionType;
import com.insurance.exception.InsuranceException;
import com.insurance.model.HomePayment;
import com.insurance.repository.HomePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomePaymentService {
    @Autowired
    HomePaymentRepository homePaymentRepository;

    public Iterable<HomePayment> getAll() {
        return homePaymentRepository.findAll();
    }

    public HomePayment save(HomePayment r) {
        return homePaymentRepository.save(r);
    }

    public HomePayment findByHomePayment(int homePaymentno) throws InsuranceException {
        HomePayment homePayment = homePaymentRepository.findById(homePaymentno).orElse(null);

        if (homePayment != null) {
            return homePayment;
        } else {
            throw new InsuranceException(ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getCode(),
                    ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getMsg());
        }
    }

    public HomePayment findByHomePaymentId(int homePaymentId) {
        return homePaymentRepository.findById(homePaymentId).orElse(null);

    }

    public void delete(HomePayment r) {
        homePaymentRepository.delete(r);
    }
}