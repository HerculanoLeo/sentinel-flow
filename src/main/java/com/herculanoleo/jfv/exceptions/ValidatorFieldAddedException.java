package com.herculanoleo.jfv.exceptions;

import com.herculanoleo.jfv.models.Field;

public class ValidatorFieldAddedException extends RuntimeException {

    public <V> ValidatorFieldAddedException(Field<V> field) {
        super("field with name: %s has already been added".formatted(field.name()));
    }

}
