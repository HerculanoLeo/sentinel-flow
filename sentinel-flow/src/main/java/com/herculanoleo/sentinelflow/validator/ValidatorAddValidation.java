package com.herculanoleo.sentinelflow.validator;

import com.herculanoleo.sentinelflow.validations.Validation;

/**
 * Fluent step for attaching validation rules to a single field.
 *
 * @param <E> parent object type
 * @param <V> field value type
 */
public interface ValidatorAddValidation<E, V> {

    /**
     * Adds a validation rule to the current field.
     *
     * @param validation rule to apply
     * @return this step for chaining more rules
     */
    ValidatorAddValidation<E, V> add(Validation<V> validation);

    /**
     * Finishes the current field and returns to the parent validator.
     *
     * @return parent validator to add more fields
     */
    Validator<E> end();

}
