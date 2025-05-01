package com.herculanoleo.sentinelflow.validations;

import com.herculanoleo.sentinelflow.models.ValidationResult;

/**
 * Represents a functional interface for validating values of a generic type.
 * This interface provides a method to validate an instance of type {@code <V>} and return a {@link ValidationResult}.
 * Implementations of this interface define specific validation rules.
 *
 * @param <V> the type of objects to be validated
 */
@FunctionalInterface
public interface Validation<V> {

    /**
     * Validates a value of type {@code <V>} and returns a {@link ValidationResult}.
     *
     * @param value The object to be validated.
     * @return A {@link ValidationResult} indicating whether the validation was successful and providing an optional message describing the outcome.
     */
    ValidationResult validate(V value);

}
