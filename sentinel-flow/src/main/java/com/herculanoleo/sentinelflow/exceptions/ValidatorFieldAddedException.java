package com.herculanoleo.sentinelflow.exceptions;

import com.herculanoleo.sentinelflow.models.Field;

public class ValidatorFieldAddedException extends RuntimeException {

    public <V> ValidatorFieldAddedException(Field<V> field) {
        super("field with name: %s has already been added".formatted(field.name()));
    }

}
