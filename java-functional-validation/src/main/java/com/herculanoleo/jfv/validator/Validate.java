package com.herculanoleo.jfv.validator;

import com.herculanoleo.jfv.exceptions.ValidatorException;
import com.herculanoleo.jfv.validations.ValidationFactory;

@FunctionalInterface
public interface Validate {

    void validate(ValidatorFactory factory, ValidationFactory validations) throws ValidatorException;

}
