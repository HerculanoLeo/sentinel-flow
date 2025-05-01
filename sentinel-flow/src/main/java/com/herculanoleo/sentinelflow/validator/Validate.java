package com.herculanoleo.sentinelflow.validator;

import com.herculanoleo.sentinelflow.exceptions.ValidatorException;
import com.herculanoleo.sentinelflow.validations.ValidationFactory;

/**
 * Represents a functional interface for validating objects using factories and validation rules.
 * Implementations of this interface define how to validate specific types or objects.
 * The {@code validate} method takes two parameters: a factory for creating validators,
 * and a set of predefined validation rules. It throws a {@link ValidatorException} if any errors occur during the validation process.
 */
@FunctionalInterface
public interface Validate {

    /**
     * Validates an object using provided factories and validation rules.
     * This method accepts a factory to create validators and a set of predefined validation rules.
     * If any errors occur during the validation process, it throws a {@link ValidatorException}.
     *
     * @param factory     A factory for creating validators used to validate the object.
     * @param validations The set of predefined validation rules to be applied during validation.
     * @throws ValidatorException if an error occurs during the validation process.
     */
    void validate(ValidatorFactory factory, ValidationFactory validations) throws ValidatorException;

}
