package com.insurance.service;

import com.insurance.exception.ExceptionType;
import com.insurance.exception.InsuranceException;
import com.insurance.model.Automobile;
import com.insurance.model.Home;
import com.insurance.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
    @Autowired
    HomeRepository homeRepository;

    public Iterable<Home> getAll() {
        return homeRepository.findAll();
    }

    public Home save(Home r) {
        return homeRepository.save(r);
    }

    public Home findByHome(int homeno) throws InsuranceException {
        Home home = homeRepository.findById(homeno).orElse(null);

        if (home != null) {
            return home;
        } else {
            throw new InsuranceException(ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getCode(),
                    ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getMsg());
        }
    }

    public Home findByHomeId(int homeId) {
        return homeRepository.findById(homeId).orElse(null);

    }

    public void delete(Home r) {
        homeRepository.delete(r);
    }
}
