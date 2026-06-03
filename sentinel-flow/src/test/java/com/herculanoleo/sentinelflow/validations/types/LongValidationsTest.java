package com.herculanoleo.sentinelflow.validations.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LongValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_eq() {
        var validation = validationFactory.eq(1L, "message");
        var result = validation.validate(1L);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_eq() {
        var validation = validationFactory.eq(1L, "message");
        var result = validation.validate(2L);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_min() {
        var validation = validationFactory.min(2L, "message");
        var result = validation.validate(2L);
        assertTrue(result.valid());
    }

    @Test
    void test_valid_min_null() {
        var validation = validationFactory.min(2L, "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_min() {
        var validation = validationFactory.min(2L, "message");
        var result = validation.validate(1L);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_max() {
        var validation = validationFactory.max(2L, "message");
        var result = validation.validate(2L);
        assertTrue(result.valid());
    }

    @Test
    void test_valid_max_null() {
        var validation = validationFactory.max(2L, "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_max() {
        var validation = validationFactory.max(1L, "message");
        var result = validation.validate(2L);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_between() {
        var validation = validationFactory.between(2L, 5L, "message");
        assertTrue(validation.validate(3L).valid());
    }

    @Test
    void test_invalid_between() {
        assertFalse(validationFactory.between(2L, 5L, "message").validate(6L).valid());
    }

    @Test
    void test_valid_gt() {
        assertTrue(validationFactory.gt(0L, "message").validate(1L).valid());
    }

    @Test
    void test_invalid_gt() {
        assertFalse(validationFactory.gt(0L, "message").validate(0L).valid());
    }

    @Test
    void test_valid_lt() {
        assertTrue(validationFactory.lt(0L, "message").validate(-1L).valid());
    }

    @Test
    void test_invalid_lt() {
        assertFalse(validationFactory.lt(0L, "message").validate(1L).valid());
    }

    @Test
    void test_valid_zero() {
        assertTrue(validationFactory.eq(0L, "message").validate(0L).valid());
    }

}
