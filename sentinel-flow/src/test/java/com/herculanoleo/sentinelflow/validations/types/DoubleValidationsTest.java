package com.herculanoleo.sentinelflow.validations.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoubleValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_eq() {
        var validation = validationFactory.eq(1.0, "message");
        var result = validation.validate(1.0);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_eq() {
        var validation = validationFactory.eq(1.0, "message");
        var result = validation.validate(2.0);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_min() {
        var validation = validationFactory.min(2.0, "message");
        var result = validation.validate(2.0);
        assertTrue(result.valid());
    }

    @Test
    void test_valid_min_null() {
        var validation = validationFactory.min(2.0, "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_min() {
        var validation = validationFactory.min(2.0, "message");
        var result = validation.validate(1.0);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_max() {
        var validation = validationFactory.max(2.0, "message");
        var result = validation.validate(2.0);
        assertTrue(result.valid());
    }

    @Test
    void test_valid_max_null() {
        var validation = validationFactory.max(2.0, "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_max() {
        var validation = validationFactory.max(1.0, "message");
        var result = validation.validate(2.0);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_between() {
        var validation = validationFactory.between(1.0, 3.0, "message");
        assertTrue(validation.validate(2.0).valid());
    }

    @Test
    void test_invalid_between() {
        assertFalse(validationFactory.between(1.0, 3.0, "message").validate(4.0).valid());
    }

    @Test
    void test_valid_gt() {
        assertTrue(validationFactory.gt(0.0, "message").validate(1.0).valid());
    }

    @Test
    void test_invalid_gt() {
        assertFalse(validationFactory.gt(0.0, "message").validate(0.0).valid());
    }

    @Test
    void test_valid_lt() {
        assertTrue(validationFactory.lt(0.0, "message").validate(-1.0).valid());
    }

    @Test
    void test_invalid_lt() {
        assertFalse(validationFactory.lt(0.0, "message").validate(1.0).valid());
    }

}
