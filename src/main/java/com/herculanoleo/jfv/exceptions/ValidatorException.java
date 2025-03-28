package com.herculanoleo.jfv.exceptions;

import com.herculanoleo.jfv.models.ValidatorFieldErrorMessages;

import java.util.Collection;

public class ValidatorException extends Exception {

    private final Collection<ValidatorFieldErrorMessages> fieldErrors;

    public ValidatorException(String message, Collection<ValidatorFieldErrorMessages> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }

    public Collection<ValidatorFieldErrorMessages> getFieldErrors() {
        return fieldErrors;
    }

}
