package com.herculanoleo.jfv.validator.impl;

import com.herculanoleo.jfv.validations.Validation;
import com.herculanoleo.jfv.validator.Validator;
import com.herculanoleo.jfv.validator.ValidatorAddValidation;
import com.herculanoleo.jfv.validator.ValidatorField;

import java.util.Objects;

public class ValidatorAddValidationImpl<E, V> implements ValidatorAddValidation<E, V> {

    private final Validator<E> validator;

    private final ValidatorField<V> validatorField;

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
