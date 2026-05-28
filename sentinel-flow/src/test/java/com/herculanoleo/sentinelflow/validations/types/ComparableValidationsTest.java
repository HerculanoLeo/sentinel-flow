package com.herculanoleo.sentinelflow.validations.types;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ComparableValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_comparableEq() {
        var expected = OffsetDateTime.of(2025, 3, 31, 9, 30, 0, 0, ZoneOffset.UTC);
        var validation = validationFactory.comparableEq(expected, "message");
        var result = validation.validate(expected);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_comparableEq() {
        var expected = OffsetDateTime.of(2025, 3, 31, 9, 30, 0, 0, ZoneOffset.UTC);
        assertFalse(validationFactory.comparableEq(expected, "message")
                .validate(expected.plusHours(1)).valid());
    }

    @Test
    void test_valid_comparableMin() {
        var min = OffsetDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        assertTrue(validationFactory.comparableMin(min, "message")
                .validate(OffsetDateTime.of(2025, 6, 1, 12, 0, 0, 0, ZoneOffset.UTC)).valid());
    }

    @Test
    void test_invalid_comparableMin() {
        var min = OffsetDateTime.of(2025, 6, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        assertFalse(validationFactory.comparableMin(min, "message")
                .validate(OffsetDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)).valid());
    }

    @Test
    void test_valid_comparableMax() {
        var max = OffsetDateTime.of(2025, 12, 31, 23, 59, 0, 0, ZoneOffset.UTC);
        assertTrue(validationFactory.comparableMax(max, "message")
                .validate(OffsetDateTime.of(2025, 6, 1, 12, 0, 0, 0, ZoneOffset.UTC)).valid());
    }

    @Test
    void test_invalid_comparableMax() {
        var max = OffsetDateTime.of(2025, 6, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        assertFalse(validationFactory.comparableMax(max, "message")
                .validate(OffsetDateTime.of(2025, 12, 31, 0, 0, 0, 0, ZoneOffset.UTC)).valid());
    }

    @Test
    void test_valid_comparableBetween() {
        var min = OffsetDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        var max = OffsetDateTime.of(2025, 12, 31, 23, 59, 0, 0, ZoneOffset.UTC);
        var validation = validationFactory.comparableBetween(min, max, "message");
        assertTrue(validation.validate(OffsetDateTime.of(2025, 6, 1, 12, 0, 0, 0, ZoneOffset.UTC)).valid());
    }

    @Test
    void test_invalid_comparableBetween() {
        var min = OffsetDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        var max = OffsetDateTime.of(2025, 12, 31, 23, 59, 0, 0, ZoneOffset.UTC);
        assertFalse(validationFactory.comparableBetween(min, max, "message")
                .validate(OffsetDateTime.of(2026, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)).valid());
    }

}
