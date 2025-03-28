package com.herculanoleo.jfv.validations;

import com.herculanoleo.jfv.models.ValidationResult;

@FunctionalInterface
public interface Validation<V> {

    ValidationResult validate(V value);

}
