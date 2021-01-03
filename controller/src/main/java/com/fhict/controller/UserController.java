package com.fhict.controller;

import com.fhict.model.User;
import com.fhict.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final CustomUserDetailsService userService;

    public UserController(CustomUserDetailsService userService) {
        this.userService = userService;
    }
//
//    @GetMapping
//    public List<User> getUsers() {
//        return userService.getUsers();
//    }
}
