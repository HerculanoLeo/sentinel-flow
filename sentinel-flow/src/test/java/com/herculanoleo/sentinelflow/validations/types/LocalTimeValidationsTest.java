package com.herculanoleo.sentinelflow.validations.types;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalTimeValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_eq() {
        var validation = validationFactory.eq(LocalTime.of(9, 30), "message");
        var result = validation.validate(LocalTime.of(9, 30));
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_eq() {
        var validation = validationFactory.eq(LocalTime.of(9, 30), "message");
        var result = validation.validate(LocalTime.of(9, 31));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_min() {
        var validation = validationFactory.min(LocalTime.of(9, 30), "message");
        var result = validation.validate(LocalTime.of(9, 30));
        assertTrue(result.valid());
    }

    @Test
    void test_valid_min_null() {
        var validation = validationFactory.min(LocalTime.of(9, 30), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_min() {
        var validation = validationFactory.min(LocalTime.of(9, 30), "message");
        var result = validation.validate(LocalTime.of(9, 29));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_max() {
        var validation = validationFactory.max(LocalTime.of(9, 30), "message");
        var result = validation.validate(LocalTime.of(9, 30));
        assertTrue(result.valid());
    }

    @Test
    void test_valid_max_null() {
        var validation = validationFactory.max(LocalTime.of(9, 30), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_max() {
        var validation = validationFactory.max(LocalTime.of(9, 30), "message");
        var result = validation.validate(LocalTime.of(9, 31));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

}
