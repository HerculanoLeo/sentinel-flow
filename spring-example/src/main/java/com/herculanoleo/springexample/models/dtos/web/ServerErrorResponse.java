package com.herculanoleo.springexample.models.dtos.web;

import java.time.OffsetDateTime;

public record ServerErrorResponse(
        OffsetDateTime timestamp,
        String message
) {
}
