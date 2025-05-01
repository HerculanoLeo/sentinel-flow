package com.herculanoleo.sentinelflow.validator;

import com.herculanoleo.sentinelflow.exceptions.ValidatorException;

import java.util.function.Function;

/**
 * The `Validator` interface defines a contract for objects that validate a specific type of value.
 * It provides methods to add validators for fields and to perform validation on the entire object.
 */
public interface Validator<V> {

    /**
     * Adds a validator for a specific field using a function to capture the value of the field from the main object.
     * This method allows chaining validators for different fields dynamically at runtime.
     *
     * @param fieldName The name of the field to be validated.
     * @param capture   A function that captures the value of the specified field from the main object.
     * @return A builder interface to further configure the validator for this field.
     */
    <R> ValidatorAddValidation<V, R> field(String fieldName, Function<V, R> capture);

    /**
     * Adds a validator for a specific field to the list of validators. This method allows dynamic addition of validators at runtime.
     *
     * @param <R> The type of the value associated with the field being validated.
     * @param validatorField The {@link ValidatorField} object representing the field and its validations.
     */
    <R> void addValidatorField(ValidatorField<R> validatorField);

    /**
     * This method performs validation on the object being validated. It checks all added validators for their respective fields and throws a {@link ValidatorException} if any field
     *  is found to be invalid.
     * @throws ValidatorException If there are any invalid fields during validation.
     */
    void validate() throws ValidatorException;

}
