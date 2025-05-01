package com.herculanoleo.sentinelflow.models;

import java.util.Collection;

/**
 * Represents a collection of error messages for specific fields that failed validation.
 * This record encapsulates the name of a field and a collection of error messages associated with it.
 */
public record ValidatorFieldErrorMessages(
        String fieldName,
        Collection<String> messages
) {
}
