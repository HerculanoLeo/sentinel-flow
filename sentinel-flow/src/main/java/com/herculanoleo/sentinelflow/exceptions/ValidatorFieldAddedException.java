package com.herculanoleo.sentinelflow.exceptions;

import com.herculanoleo.sentinelflow.models.Field;

/**
 * This exception is thrown when an attempt is made to add a field that has already been added to the validator.
 */
public class ValidatorFieldAddedException extends RuntimeException {

    public <V> ValidatorFieldAddedException(Field<V> field) {
        super("field with name: %s has already been added".formatted(field.name()));
    }

}
