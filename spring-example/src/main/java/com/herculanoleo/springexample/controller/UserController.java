package com.herculanoleo.springexample.controller;

import com.herculanoleo.sentinelflow.exceptions.ValidatorException;
import com.herculanoleo.springexample.models.dtos.user.UserRegistrationDTO;
import com.herculanoleo.springexample.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody UserRegistrationDTO requestEntity) throws ValidatorException {
        service.register(requestEntity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
