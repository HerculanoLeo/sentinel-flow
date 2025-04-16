package com.herculanoleo.sentinelflow.validations;

import com.herculanoleo.sentinelflow.models.ValidationResult;

@FunctionalInterface
public interface Validation<V> {

    ValidationResult validate(V value);

}
