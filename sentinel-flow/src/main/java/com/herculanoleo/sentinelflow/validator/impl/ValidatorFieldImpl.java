package com.herculanoleo.sentinelflow.validator.impl;

import com.herculanoleo.sentinelflow.models.Field;
import com.herculanoleo.sentinelflow.models.Result;
import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validator.ValidatorField;

import java.util.LinkedList;
import java.util.List;

public class ValidatorFieldImpl<V> implements ValidatorField<V> {

    private final Field<V> field;

    private final List<Validation<V>> validations = new LinkedList<>();

    public ValidatorFieldImpl(Field<V> field) {
        this.field = field;
    }

    public Field<V> getField() {
        return this.field;
    }

    public void addValidation(Validation<V> validation) {
        validations.add(validation);
    }

    public Result<V> build() {
        var results = this.validations.parallelStream()
                .map(validation -> validation.validate(field.value()))
                .toList();
        return new Result<>(field, results);
    }

}
