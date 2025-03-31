package com.herculanoleo.jfv.models;

import java.util.Collection;

public record Result<V>(
        Field<V> field,
        Collection<ValidationResult> results
) {
}
