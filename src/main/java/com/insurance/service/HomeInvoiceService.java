package com.insurance.service;

import com.insurance.exception.ExceptionType;
import com.insurance.exception.InsuranceException;
import com.insurance.model.HomeInvoice;
import com.insurance.repository.HomeInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeInvoiceService {
    @Autowired
    HomeInvoiceRepository homeInvoiceRepository;

    public Iterable<HomeInvoice> getAll() {
        return homeInvoiceRepository.findAll();
    }

    public HomeInvoice save(HomeInvoice r) {
        return homeInvoiceRepository.save(r);
    }

    public HomeInvoice findByHomeInvoice(int homeInvoiceno) throws InsuranceException {
        HomeInvoice homeInvoice = homeInvoiceRepository.findById(homeInvoiceno).orElse(null);

        if (homeInvoice != null) {
            return homeInvoice;
        } else {
            throw new InsuranceException(ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getCode(),
                    ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getMsg());
        }
    }

    public void delete(HomeInvoice r) {
        homeInvoiceRepository.delete(r);
    }
}