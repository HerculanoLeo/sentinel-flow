package com.herculanoleo.sentinelflow.validator.impl;

import com.herculanoleo.sentinelflow.models.Field;
import com.herculanoleo.sentinelflow.validator.ValidatorAbstraction;
import com.herculanoleo.sentinelflow.validator.ValidatorAddValidation;
import com.herculanoleo.sentinelflow.validator.ValidatorField;

import java.util.List;

public class ValidatorImpl<E> extends ValidatorAbstraction<E> {

    public ValidatorImpl(E value) {
        super(value);
    }

    @Override
    public <V> ValidatorAddValidation<E, V> field(Field<V> field) {
        return new ValidatorAddValidationImpl<>(this, new ValidatorFieldImpl<>(field));
    }

    protected List<ValidatorField<?>> getValidatorFields() {
        return validatorFields;
    }

}
