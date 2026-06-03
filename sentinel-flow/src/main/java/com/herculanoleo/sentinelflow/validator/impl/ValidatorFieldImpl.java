package com.herculanoleo.sentinelflow.validator.impl;

import com.herculanoleo.sentinelflow.models.Field;
import com.herculanoleo.sentinelflow.models.Result;
import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validator.ValidatorField;

import java.util.LinkedList;
import java.util.List;

/**
 * Default implementation of {@link ValidatorField}.
 *
 * @param <V> field value type
 */
public class ValidatorFieldImpl<V> implements ValidatorField<V> {

    private final Field<V> field;

    private final List<Validation<V>> validations = new LinkedList<>();

    /**
     * Creates a field validator for the given field.
     *
     * @param field field metadata and captured value
     */
    public ValidatorFieldImpl(Field<V> field) {
        this.field = field;
    }

    @Override
    public Field<V> getField() {
        return this.field;
    }

    @Override
    public void addValidation(Validation<V> validation) {
        validations.add(validation);
    }

    @Override
    public Result<V> build() {
        var results = this.validations.parallelStream()
                .map(validation -> validation.validate(field.value()))
                .toList();
        return new Result<>(field, results);
    }

}
