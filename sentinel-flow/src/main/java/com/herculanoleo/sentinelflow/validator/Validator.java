package com.herculanoleo.sentinelflow.validator;

import com.herculanoleo.sentinelflow.exceptions.ValidatorException;

import java.util.function.Function;

/**
 * Fluent builder for validating fields of a single object.
 *
 * <p>Start with {@link #field(String, Function)}, chain {@code .add(...)} rules,
 * then call {@code .end()} to return to this validator and add more fields.
 *
 * @param <V> the type of object being validated
 */
public interface Validator<V> {

    /**
     * Starts validation for a field extracted from the object.
     *
     * @param fieldName field name used in error messages
     * @param capture   function that extracts the field value from the object
     * @param <R>       field value type
     * @return fluent step to add validation rules
     */
    <R> ValidatorAddValidation<V, R> field(String fieldName, Function<V, R> capture);

    /**
     * Registers a pre-built field validator.
     *
     * @param validatorField field validator to add
     * @param <R>            field value type
     */
    <R> void addValidatorField(ValidatorField<R> validatorField);

    /**
     * Runs all registered field validations.
     *
     * @throws ValidatorException when any field fails validation
     */
    void validate() throws ValidatorException;

}
