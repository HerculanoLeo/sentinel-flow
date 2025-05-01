package com.herculanoleo.sentinelflow.exceptions;

import com.herculanoleo.sentinelflow.models.ValidatorFieldErrorMessages;

import java.util.Collection;

/**
 * This exception is thrown when validation fails. It contains a collection of field-level error messages that detail the issues found during validation.
 */
public class ValidatorException extends Exception {

    private final Collection<ValidatorFieldErrorMessages> fieldErrors;

    public ValidatorException(String message, Collection<ValidatorFieldErrorMessages> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }

    /**
     * Retrieves the collection of field-level error messages that detail validation failures.
     * @return A collection of {@link ValidatorFieldErrorMessages} objects, each containing the name of a field and its associated error messages.
     */
    public Collection<ValidatorFieldErrorMessages> getFieldErrors() {
        return fieldErrors;
    }

}
