package com.herculanoleo.jfv.models;

public record ValidationResult(
       boolean valid,
       String message
) {
}
