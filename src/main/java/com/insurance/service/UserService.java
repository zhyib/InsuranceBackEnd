package com.insurance.service;

import com.insurance.exception.ExceptionType;
import com.insurance.exception.InsuranceException;
import com.insurance.model.User;
import com.insurance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User r) {
        return userRepository.save(r);
    }

    public User findByUser(int userno) throws InsuranceException {
        User user = userRepository.findById(userno).orElse(null);

        if (user != null) {
            return user;
        } else {
            throw new InsuranceException(ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getCode(),
                    ExceptionType.ROOM_FIND_BY_ROOMNO_ERROR.getMsg());
        }
    }

    public User findByUserId(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void delete(User r) {
        userRepository.delete(r);
    }
}
