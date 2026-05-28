package com.herculanoleo.sentinelflow.exceptions;

import com.herculanoleo.sentinelflow.models.Field;

/**
 * Thrown when attempting to register the same field twice on a validator.
 */
public class ValidatorFieldAddedException extends RuntimeException {

    /**
     * Creates an exception for a duplicate field registration.
     *
     * @param <V>   field value type
     * @param field field that was already added
     */
    public <V> ValidatorFieldAddedException(Field<V> field) {
        super("field with name: %s has already been added".formatted(field.name()));
    }

}
