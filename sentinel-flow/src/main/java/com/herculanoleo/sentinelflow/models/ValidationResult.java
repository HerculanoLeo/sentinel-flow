package com.herculanoleo.sentinelflow.models;

public record ValidationResult(
       boolean valid,
       String message
) {
}
