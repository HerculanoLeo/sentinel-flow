package com.herculanoleo.sentinelflow.exceptions;

import com.herculanoleo.sentinelflow.models.ValidatorFieldErrorMessages;

import java.util.Collection;

/**
 * Thrown when one or more fields fail validation.
 *
 * <p>Use {@link #getFieldErrors()} to retrieve per-field error messages.
 */
public class ValidatorException extends Exception {

    /** Validation errors grouped by field name. */
    private final Collection<ValidatorFieldErrorMessages> fieldErrors;

    /**
     * Creates a validation exception with field errors.
     *
     * @param message     general error message
     * @param fieldErrors validation errors grouped by field
     */
    public ValidatorException(String message, Collection<ValidatorFieldErrorMessages> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }

    /**
     * Returns validation errors grouped by field name.
     *
     * @return validation errors grouped by field name
     */
    public Collection<ValidatorFieldErrorMessages> getFieldErrors() {
        return fieldErrors;
    }

}
