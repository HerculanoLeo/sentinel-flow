package com.herculanoleo.sentinelflow.models;

import java.util.Collection;

/**
 * Error messages grouped by field name.
 *
 * @param fieldName field that failed validation
 * @param messages  error messages from failed rules
 */
public record ValidatorFieldErrorMessages(
        String fieldName,
        Collection<String> messages
) {
}
