package com.herculanoleo.sentinelflow.validator.impl;

import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validator.Validator;
import com.herculanoleo.sentinelflow.validator.ValidatorAddValidation;
import com.herculanoleo.sentinelflow.validator.ValidatorField;

import java.util.Objects;

/**
 * Default implementation of {@link ValidatorAddValidation}.
 *
 * @param <E> parent object type
 * @param <V> field value type
 */
public class ValidatorAddValidationImpl<E, V> implements ValidatorAddValidation<E, V> {

    private final Validator<E> validator;

    private final ValidatorField<V> validatorField;

    /**
     * Creates a fluent step for attaching rules to a field.
     *
     * @param validator      parent validator
     * @param validatorField field validator being configured
     */
    public ValidatorAddValidationImpl(Validator<E> validator, ValidatorField<V> validatorField) {
        this.validator = validator;
        this.validatorField = validatorField;
    }

    @Override
    public ValidatorAddValidation<E, V> add(Validation<V> validation) {
        Objects.requireNonNull(validation);
        validatorField.addValidation(validation);
        return this;
    }

    @Override
    public Validator<E> end() {
        validator.addValidatorField(validatorField);
        return validator;
    }

}
