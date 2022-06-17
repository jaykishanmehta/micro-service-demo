package com.jay.fusion.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.fusion.user.dto.ResponseTemplateVO;
import com.jay.fusion.user.entity.User;
import com.jay.fusion.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("Inside saveUser of UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId, @RequestHeader("test-header") String header
    		, @RequestHeader(value = "gateway-header", required = false) String gatewayHeader) {
        log.info("Inside getUserWithDepartment of UserController");
        log.info(">> Header value {}, Gateway Header {}", header, gatewayHeader);
        return userService.getUserWithDepartment(userId);
    }


}