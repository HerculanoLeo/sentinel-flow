package com.herculanoleo.sentinelflow.validator;

import com.herculanoleo.sentinelflow.exceptions.ValidatorException;
import com.herculanoleo.sentinelflow.validations.ValidationFactory;

@FunctionalInterface
public interface Validate {

    void validate(ValidatorFactory factory, ValidationFactory validations) throws ValidatorException;

}
