package com.herculanoleo.sentinelflow.validator.impl;

import com.herculanoleo.sentinelflow.models.Field;
import com.herculanoleo.sentinelflow.validator.ValidatorAbstraction;
import com.herculanoleo.sentinelflow.validator.ValidatorAddValidation;
import com.herculanoleo.sentinelflow.validator.ValidatorField;

import java.util.List;

/**
 * Default implementation of {@link com.herculanoleo.sentinelflow.validator.Validator}.
 *
 * @param <E> object type being validated
 */
public class ValidatorImpl<E> extends ValidatorAbstraction<E> {

    /**
     * @param value object to validate
     */
    public ValidatorImpl(E value) {
        super(value);
    }

    @Override
    public <V> ValidatorAddValidation<E, V> field(Field<V> field) {
        return new ValidatorAddValidationImpl<>(this, new ValidatorFieldImpl<>(field));
    }

    /**
     * @return registered field validators (for testing)
     */
    protected List<ValidatorField<?>> getValidatorFields() {
        return validatorFields;
    }

}
