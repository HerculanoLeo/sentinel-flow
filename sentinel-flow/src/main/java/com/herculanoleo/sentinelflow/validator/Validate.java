package com.herculanoleo.sentinelflow.validator;

import com.herculanoleo.sentinelflow.exceptions.ValidatorException;
import com.herculanoleo.sentinelflow.validations.ValidationFactory;

/**
 * Functional contract for a validation routine.
 *
 * <p>Implementations receive a {@link ValidatorFactory} and a {@link ValidationFactory}
 * so validation logic stays decoupled from concrete implementations.
 *
 * @see ValidatorFactory
 * @see ValidationFactory
 */
@FunctionalInterface
public interface Validate {

    /**
     * Runs the validation logic.
     *
     * @param factory      factory used to create validators for objects
     * @param validations  factory used to create validation rules
     * @throws ValidatorException when one or more fields fail validation
     */
    void validate(ValidatorFactory factory, ValidationFactory validations) throws ValidatorException;

}
