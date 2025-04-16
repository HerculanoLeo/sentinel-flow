package com.herculanoleo.springexample.models.dtos.web;

import com.herculanoleo.sentinelflow.models.ValidatorFieldErrorMessages;

import java.time.OffsetDateTime;
import java.util.Collection;

public record ServerValidationErrorResponse(
        OffsetDateTime timestamp,
        String message,
        Collection<ValidatorFieldErrorMessages> fieldErrors
) {
}
