package com.herculanoleo.springexample.service;

import com.herculanoleo.sentinelflow.exceptions.ValidatorException;
import com.herculanoleo.sentinelflow.validations.ValidationFactory;
import com.herculanoleo.sentinelflow.validator.ValidatorFactory;
import com.herculanoleo.springexample.models.dtos.user.UserRegistrationDTO;
import com.herculanoleo.springexample.models.validates.UserRegistrationValidate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final ValidatorFactory validator;

    private final ValidationFactory validation;

    public UserService(ValidatorFactory validator, ValidationFactory validation) {
        this.validator = validator;
        this.validation = validation;
    }

    public void register(UserRegistrationDTO dto) throws ValidatorException {
        UserRegistrationValidate.of(dto).validate(validator, validation);

        //After validating the DTO, you can save user into the database, call other services, etc. For example
    }

}
