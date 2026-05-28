package com.herculanoleo.sentinelflow.validations.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BooleanValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_isTrue() {
        var validation = validationFactory.isTrue("message");
        var result = validation.validate(Boolean.TRUE);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_isTrue() {
        var validation = validationFactory.isTrue("message");
        var result = validation.validate(Boolean.FALSE);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_invalid_isTrue_null() {
        assertFalse(validationFactory.isTrue("message").validate(null).valid());
    }

    @Test
    void test_valid_isFalse() {
        var validation = validationFactory.isFalse("message");
        var result = validation.validate(Boolean.FALSE);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_isFalse() {
        var validation = validationFactory.isFalse("message");
        var result = validation.validate(Boolean.TRUE);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

}
