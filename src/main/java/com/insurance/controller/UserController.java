package com.insurance.controller;

import com.insurance.model.Result;
import com.insurance.model.User;
import com.insurance.service.UserService;
import com.insurance.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/login/{username}")
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResultReturn.error(2, "no user");
        }
        if (user.getUserPwd().equals(password)) {
            return ResultReturn.success(user);
        } else  {
            return ResultReturn.error(2, "wrong username or password");
        }
    }

    @RequestMapping("/user/getAll")
    public Result<User> getAll() {
        return ResultReturn.success(userService.getAll());
    }

    @RequestMapping("/user/add")
    public Result userAdd(@RequestParam("userId") int userId,
                          @RequestParam("purchaseDate") Date purchaseDate, @RequestParam("purchaseValue") double purchaseValue, @RequestParam("area") double area,
                          @RequestParam("type") char type, @RequestParam("autoFireNotification") int autoFireNotification, @RequestParam("userSecuritySystem") int userSecuritySystem,
                          @RequestParam("swimmingPool") char swimmingPool,
                          @RequestParam("basement") int basement, @RequestParam("hiId") int hiId) {
        User e = userService.findByUserId(userId);
        if (e != null)
            return ResultReturn.error(2, "that User already exist");
        else {
            e = saveUser();
            return ResultReturn.success(userService.save(e));
        }
    }

    @RequestMapping("/user/update/{userId}")
    public Result userUpdate(@PathVariable("userId") int userId,
                             @RequestParam("purchaseDate") Date purchaseDate, @RequestParam("purchaseValue") double purchaseValue, @RequestParam("area") double area,
                             @RequestParam("type") char type, @RequestParam("autoFireNotification") int autoFireNotification, @RequestParam("userSecuritySystem") int userSecuritySystem,
                             @RequestParam("swimmingPool") char swimmingPool,
                             @RequestParam("basement") int basement, @RequestParam("hiId") int hiId) {
        User e = userService.findByUserId(userId);
        if (e == null) {
            return ResultReturn.error(1, "that userId did not exist");
        } else {
            e = saveUser();
            return ResultReturn.success(userService.save(e));
        }

    }

    public User saveUser() {
        return new User();
    }
}
