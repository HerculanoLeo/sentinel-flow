package com.herculanoleo.sentinelflow.models;

/**
 * Outcome of a single validation rule.
 *
 * @param valid   {@code true} when the value passed validation
 * @param message error message when {@code valid} is {@code false}; {@code null} otherwise
 */
public record ValidationResult(
       boolean valid,
       String message
) {
}
