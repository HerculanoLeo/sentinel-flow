package com.herculanoleo.sentinelflow.validator.impl;

import com.herculanoleo.sentinelflow.models.Field;
import com.herculanoleo.sentinelflow.validator.Validator;
import com.herculanoleo.sentinelflow.validator.ValidatorAbstraction;
import com.herculanoleo.sentinelflow.validator.ValidatorAddValidation;
import com.herculanoleo.sentinelflow.validator.ValidatorField;

import java.util.List;

/**
 * The `ValidatorImpl` class is a concrete implementation of the {@link Validator} interface, extending {@link ValidatorAbstraction}.
 * It provides methods to add fields for validation and retrieve the list of validator fields.
 *
 * @param <E> The type of the entity being validated.
 */
public class ValidatorImpl<E> extends ValidatorAbstraction<E> {

    public ValidatorImpl(E value) {
        super(value);
    }

    @Override
    public <V> ValidatorAddValidation<E, V> field(Field<V> field) {
        return new ValidatorAddValidationImpl<>(this, new ValidatorFieldImpl<>(field));
    }

    /**
     * Retrieves the list of validator fields managed by this implementation.
     * This method provides access to the internal collection of {@link ValidatorField} objects,
     * which are responsible for validating specific fields in a validated entity.
     *
     * @return A list containing all {@link ValidatorField} instances associated with this validator.
     */
    protected List<ValidatorField<?>> getValidatorFields() {
        return validatorFields;
    }

}
