package com.herculanoleo.sentinelflow.models;

/**
 * Represents a field with a name and a value of type V.
 * This record is used to encapsulate data fields in various components, such as results or validations.
 *
 * @param <V> the type of the value contained in the field
 */
public record Field<V>(
        String name,
        V value
) {
}
