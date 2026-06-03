package com.herculanoleo.sentinelflow.validations.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntegerValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_eq() {
        var validation = validationFactory.eq(1, "message");
        var result = validation.validate(1);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_eq() {
        var validation = validationFactory.eq(1, "message");
        var result = validation.validate(2);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_min() {
        var validation = validationFactory.min(2, "message");
        var result = validation.validate(2);
        assertTrue(result.valid());
    }

    @Test
    void test_valid_min_null() {
        var validation = validationFactory.min(2, "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_min() {
        var validation = validationFactory.min(2, "message");
        var result = validation.validate(1);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_max() {
        var validation = validationFactory.max(2, "message");
        var result = validation.validate(2);
        assertTrue(result.valid());
    }

    @Test
    void test_valid_max_null() {
        var validation = validationFactory.max(2, "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_max() {
        var validation = validationFactory.max(1, "message");
        var result = validation.validate(2);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_between() {
        var validation = validationFactory.between(2, 5, "message");
        var result = validation.validate(3);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_between() {
        var validation = validationFactory.between(2, 5, "message");
        var result = validation.validate(6);
        assertFalse(result.valid());
    }

    @Test
    void test_valid_positive() {
        var validation = validationFactory.positive("message");
        assertTrue(validation.validate(1).valid());
    }

    @Test
    void test_invalid_positive() {
        var validation = validationFactory.positive("message");
        assertFalse(validation.validate(0).valid());
    }

    @Test
    void test_valid_negative() {
        var validation = validationFactory.negative("message");
        assertTrue(validation.validate(-1).valid());
    }

    @Test
    void test_invalid_negative() {
        var validation = validationFactory.negative("message");
        assertFalse(validation.validate(1).valid());
    }

    @Test
    void test_valid_zero() {
        var validation = validationFactory.zero("message");
        assertTrue(validation.validate(0).valid());
    }

    @Test
    void test_invalid_zero() {
        var validation = validationFactory.zero("message");
        assertFalse(validation.validate(1).valid());
    }

    @Test
    void test_valid_gt() {
        assertTrue(validationFactory.gt(5, "message").validate(6).valid());
    }

    @Test
    void test_invalid_gt() {
        assertFalse(validationFactory.gt(5, "message").validate(5).valid());
    }

    @Test
    void test_valid_lt() {
        assertTrue(validationFactory.lt(5, "message").validate(4).valid());
    }

    @Test
    void test_invalid_lt() {
        assertFalse(validationFactory.lt(5, "message").validate(5).valid());
    }

}
