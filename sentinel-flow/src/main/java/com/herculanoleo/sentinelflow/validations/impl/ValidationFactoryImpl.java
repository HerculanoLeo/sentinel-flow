package com.herculanoleo.sentinelflow.validations.impl;

import com.herculanoleo.sentinelflow.models.ValidationResult;
import com.herculanoleo.sentinelflow.validations.ValidationFactory;

/**
 * Default implementation of {@link ValidationFactory}.
 *
 * <p>Validation rules are inherited as default methods from the composed interfaces.
 * This class only implements {@link com.herculanoleo.sentinelflow.validations.ValidationSupport}.
 */
public class ValidationFactoryImpl implements ValidationFactory {

    /** Creates a new validation factory. */
    public ValidationFactoryImpl() {
    }

    @Override
    public ValidationResult valid() {
        return new ValidationResult(true, null);
    }

    @Override
    public ValidationResult invalid(String message) {
        return new ValidationResult(false, message);
    }

}
