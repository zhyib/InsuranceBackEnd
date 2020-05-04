package com.insurance.service;

import com.insurance.exception.ExceptionType;
import com.insurance.exception.InsuranceException;
import com.insurance.model.Automobile;
import com.insurance.repository.AutomobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutomobileService {
    @Autowired
    AutomobileRepository automobileRepository;

    public Iterable<Automobile> getAll() {
        return automobileRepository.findAll();
    }

    public Automobile save(Automobile r) {
        return automobileRepository.save(r);
    }

    public Automobile findByAutomobile(int automobileno) throws InsuranceException {
        Automobile automobile = automobileRepository.findById(automobileno).orElse(null);

        if (automobile != null) {
            return automobile;
        } else {
            throw new InsuranceException(ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getCode(),
                    ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getMsg());
        }
    }

    public void delete(Automobile r) {
        automobileRepository.delete(r);
    }
}
