package com.herculanoleo.jfv.models;

import java.util.Collection;

public record ValidatorFieldErrorMessages(
        String fieldName,
        Collection<String> messages
) {
}
