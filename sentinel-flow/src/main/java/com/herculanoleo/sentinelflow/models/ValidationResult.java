package com.herculanoleo.sentinelflow.models;

/**
 * Represents the result of a validation check.
 * This record contains two fields: one indicating whether the validation was successful (valid) and another for an optional message describing the outcome.
 */
public record ValidationResult(
       boolean valid,
       String message
) {
}
