package com.herculanoleo.sentinelflow.validations;

import com.herculanoleo.sentinelflow.models.ValidationResult;

/**
 * Provides helpers to build {@link ValidationResult} instances inside validation rules.
 */
public interface ValidationSupport {

    /**
     * Creates a successful validation result.
     *
     * @return a successful validation result
     */
    ValidationResult valid();

    /**
     * Creates a failed validation result.
     *
     * @param message the error message
     * @return a failed validation result
     */
    ValidationResult invalid(String message);

}
