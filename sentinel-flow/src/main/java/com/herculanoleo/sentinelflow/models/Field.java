package com.herculanoleo.sentinelflow.models;

/**
 * A named field and its captured value.
 *
 * @param name  field name used in error messages
 * @param value captured field value
 * @param <V>   field value type
 */
public record Field<V>(
        String name,
        V value
) {
}
