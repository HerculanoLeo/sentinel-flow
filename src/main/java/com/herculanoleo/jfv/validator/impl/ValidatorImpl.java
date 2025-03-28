package com.herculanoleo.jfv.validator.impl;

import com.herculanoleo.jfv.models.Field;
import com.herculanoleo.jfv.validator.ValidatorAbstraction;
import com.herculanoleo.jfv.validator.ValidatorAddValidation;

public class ValidatorImpl<E> extends ValidatorAbstraction<E> {

    public ValidatorImpl(E value) {
        super(value);
    }

    @Override
    public <V> ValidatorAddValidation<E, V> field(Field<V> field) {
        return new ValidatorAddValidationImpl<>(this, new ValidatorFieldImpl<>(field));
    }

}
