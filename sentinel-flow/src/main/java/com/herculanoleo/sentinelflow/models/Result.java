package com.herculanoleo.sentinelflow.models;

import java.util.Collection;

/**
 * Represents the result of a validation process containing a field and its associated validation results.
 * This record is used to encapsulate the outcome of validating a particular field within a data structure or component.
 *
 * @param <V> the type of the value contained in the field
 */
public record Result<V>(
        Field<V> field,
        Collection<ValidationResult> results
) {
}
