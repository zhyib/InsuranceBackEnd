package com.insurance.service;

import com.insurance.exception.ExceptionType;
import com.insurance.exception.InsuranceException;
import com.insurance.model.Driver;
import com.insurance.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    @Autowired
    DriverRepository driverRepository;

    public Iterable<Driver> getAll() {
        return driverRepository.findAll();
    }

    public Driver save(Driver r) {
        return driverRepository.save(r);
    }

    public Driver findByDriver(int driverno) throws InsuranceException {
        Driver driver = driverRepository.findById(driverno).orElse(null);

        if (driver != null) {
            return driver;
        } else {
            throw new InsuranceException(ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getCode(),
                    ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getMsg());
        }
    }

    public void delete(Driver r) {
        driverRepository.delete(r);
    }
}
