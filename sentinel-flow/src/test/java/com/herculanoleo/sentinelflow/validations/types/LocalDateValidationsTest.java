package com.herculanoleo.sentinelflow.validations.types;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalDateValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_eq() {
        var validation = validationFactory.eq(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(LocalDate.of(2025, 3, 31));
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_eq() {
        var validation = validationFactory.eq(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(LocalDate.of(2025, 3, 30));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_min() {
        var validation = validationFactory.min(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(LocalDate.of(2025, 3, 31));
        assertTrue(result.valid());
    }

    @Test
    void test_valid_min_null() {
        var validation = validationFactory.min(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_min() {
        var validation = validationFactory.min(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(LocalDate.of(2025, 3, 30));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_max() {
        var validation = validationFactory.max(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(LocalDate.of(2025, 3, 31));
        assertTrue(result.valid());
    }

    @Test
    void test_valid_max_null() {
        var validation = validationFactory.max(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_max() {
        var validation = validationFactory.max(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(LocalDate.of(2025, 4, 1));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_isPast() {
        var validation = validationFactory.isPast("message");
        var result = validation.validate(LocalDate.now().minusDays(1));
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_isPast() {
        var validation = validationFactory.isPast("message");
        var result = validation.validate(LocalDate.now().plusDays(1));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_isFuture() {
        var validation = validationFactory.isFuture("message");
        var result = validation.validate(LocalDate.now().plusDays(1));
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_isFuture() {
        var validation = validationFactory.isFuture("message");
        var result = validation.validate(LocalDate.now().minusDays(1));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_between() {
        var min = LocalDate.of(2025, 1, 1);
        var max = LocalDate.of(2025, 12, 31);
        var validation = validationFactory.between(min, max, "message");
        assertTrue(validation.validate(LocalDate.of(2025, 6, 15)).valid());
    }

    @Test
    void test_invalid_between() {
        var min = LocalDate.of(2025, 1, 1);
        var max = LocalDate.of(2025, 12, 31);
        var validation = validationFactory.between(min, max, "message");
        assertFalse(validation.validate(LocalDate.of(2026, 1, 1)).valid());
    }

}
