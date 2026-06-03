package com.herculanoleo.sentinelflow.models;

import java.util.Collection;

/**
 * Aggregated validation results for a single field.
 *
 * @param field   field metadata and captured value
 * @param results outcomes of each rule applied to the field
 * @param <V>     field value type
 */
public record Result<V>(
        Field<V> field,
        Collection<ValidationResult> results
) {
}
