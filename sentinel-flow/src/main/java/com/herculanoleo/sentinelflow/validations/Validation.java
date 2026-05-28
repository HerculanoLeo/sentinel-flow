package com.herculanoleo.sentinelflow.validations;

import com.herculanoleo.sentinelflow.models.ValidationResult;

/**
 * A single validation rule applied to a value of type {@code V}.
 *
 * <p>Implementations are usually created by {@link ValidationFactory} methods
 * and executed by the validator fluent API.
 *
 * @param <V> the type of value being validated
 */
@FunctionalInterface
public interface Validation<V> {

    /**
     * Validates the given value.
     *
     * @param value the value to validate
     * @return the validation outcome
     */
    ValidationResult validate(V value);

}
