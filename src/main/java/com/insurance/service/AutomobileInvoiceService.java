package com.insurance.service;

import com.insurance.exception.ExceptionType;
import com.insurance.exception.InsuranceException;
import com.insurance.model.AutomobileInsuranceCustomer;
import com.insurance.model.AutomobileInvoice;
import com.insurance.repository.AutomobileInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutomobileInvoiceService {
    @Autowired
    AutomobileInvoiceRepository automobileInvoiceRepository;

    public Iterable<AutomobileInvoice> getAll() {
        return automobileInvoiceRepository.findAll();
    }

    public AutomobileInvoice save(AutomobileInvoice r) {
        return automobileInvoiceRepository.save(r);
    }

    public AutomobileInvoice findByAutomobileInvoice(int automobileInvoiceno) throws InsuranceException {
        AutomobileInvoice automobileInvoice = automobileInvoiceRepository.findById(automobileInvoiceno).orElse(null);

        if (automobileInvoice != null) {
            return automobileInvoice;
        } else {
            throw new InsuranceException(ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getCode(),
                    ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getMsg());
        }
    }

    public AutomobileInvoice findByAutomobileInvoiceId(int automobileInvoiceId) {
        return automobileInvoiceRepository.findById(automobileInvoiceId).orElse(null);

    }

    public void delete(AutomobileInvoice r) {
        automobileInvoiceRepository.delete(r);
    }
}
