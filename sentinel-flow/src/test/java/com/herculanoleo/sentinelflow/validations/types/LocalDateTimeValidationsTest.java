package com.herculanoleo.sentinelflow.validations.types;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalDateTimeValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_eq() {
        var validation = validationFactory.eq(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(LocalDateTime.of(2025, 3, 31, 9, 30));
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_eq() {
        var validation = validationFactory.eq(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(LocalDateTime.of(2025, 3, 31, 9, 31));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_min() {
        var validation = validationFactory.min(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(LocalDateTime.of(2025, 3, 31, 9, 30));
        assertTrue(result.valid());
    }

    @Test
    void test_valid_min_null() {
        var validation = validationFactory.min(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_min() {
        var validation = validationFactory.min(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(LocalDateTime.of(2025, 3, 31, 9, 29));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_max() {
        var validation = validationFactory.max(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(LocalDateTime.of(2025, 3, 31, 9, 30));
        assertTrue(result.valid());
    }

    @Test
    void test_valid_max_null() {
        var validation = validationFactory.max(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_max() {
        var validation = validationFactory.max(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(LocalDateTime.of(2025, 3, 31, 9, 31));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_isPastDateTime() {
        var validation = validationFactory.isPastDateTime("message");
        var result = validation.validate(LocalDateTime.now().minusHours(1));
        assertTrue(result.valid());
    }

    @Test
    void test_valid_between() {
        var min = LocalDateTime.of(2025, 1, 1, 0, 0);
        var max = LocalDateTime.of(2025, 12, 31, 23, 59);
        var validation = validationFactory.between(min, max, "message");
        assertTrue(validation.validate(LocalDateTime.of(2025, 6, 15, 12, 0)).valid());
    }

    @Test
    void test_invalid_between() {
        var min = LocalDateTime.of(2025, 1, 1, 0, 0);
        var max = LocalDateTime.of(2025, 12, 31, 23, 59);
        assertFalse(validationFactory.between(min, max, "message")
                .validate(LocalDateTime.of(2026, 1, 1, 0, 0)).valid());
    }

    @Test
    void test_invalid_isPastDateTime() {
        var validation = validationFactory.isPastDateTime("message");
        assertFalse(validation.validate(LocalDateTime.now().plusHours(1)).valid());
    }

    @Test
    void test_valid_isFutureDateTime() {
        var validation = validationFactory.isFutureDateTime("message");
        assertTrue(validation.validate(LocalDateTime.now().plusHours(1)).valid());
    }

    @Test
    void test_invalid_isFutureDateTime() {
        var validation = validationFactory.isFutureDateTime("message");
        assertFalse(validation.validate(LocalDateTime.now().minusHours(1)).valid());
    }

}
