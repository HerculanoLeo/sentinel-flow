package com.herculanoleo.sentinelflow.validations.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationFactoryImplTest {

    private final ValidationFactoryImpl validationFactory = new ValidationFactoryImpl();

    @Test
    void test_valid() {
        var result = validationFactory.valid();
        assertTrue(result.valid());
        assertNull(result.message());
    }

    @Test
    void test_invalid() {
        var result = validationFactory.invalid("message");
        assertFalse(result.valid());
        assertTrue("message".equals(result.message()));
    }

}
