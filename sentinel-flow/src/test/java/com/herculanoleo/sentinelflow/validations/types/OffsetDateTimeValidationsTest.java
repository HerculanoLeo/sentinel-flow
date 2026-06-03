package com.herculanoleo.sentinelflow.validations.types;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OffsetDateTimeValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_eq() {
        var expected = OffsetDateTime.of(2025, 3, 31, 9, 30, 0, 0, ZoneOffset.UTC);
        var validation = validationFactory.eq(expected, "message");
        var result = validation.validate(expected);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_eq() {
        var expected = OffsetDateTime.of(2025, 3, 31, 9, 30, 0, 0, ZoneOffset.UTC);
        var validation = validationFactory.eq(expected, "message");
        var result = validation.validate(expected.plusHours(1));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_min() {
        var min = OffsetDateTime.of(2025, 3, 31, 9, 30, 0, 0, ZoneOffset.UTC);
        var validation = validationFactory.min(min, "message");
        var result = validation.validate(min);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_min() {
        var min = OffsetDateTime.of(2025, 3, 31, 9, 30, 0, 0, ZoneOffset.UTC);
        var validation = validationFactory.min(min, "message");
        var result = validation.validate(min.minusHours(1));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_max() {
        var max = OffsetDateTime.of(2025, 3, 31, 9, 30, 0, 0, ZoneOffset.UTC);
        var validation = validationFactory.max(max, "message");
        var result = validation.validate(max);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_max() {
        var max = OffsetDateTime.of(2025, 3, 31, 9, 30, 0, 0, ZoneOffset.UTC);
        var validation = validationFactory.max(max, "message");
        var result = validation.validate(max.plusHours(1));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_isFutureOffsetDateTime() {
        var validation = validationFactory.isFutureOffsetDateTime("message");
        var result = validation.validate(OffsetDateTime.now().plusHours(1));
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_isFutureOffsetDateTime() {
        var validation = validationFactory.isFutureOffsetDateTime("message");
        assertFalse(validation.validate(OffsetDateTime.now().minusHours(1)).valid());
    }

    @Test
    void test_valid_isPastOffsetDateTime() {
        var validation = validationFactory.isPastOffsetDateTime("message");
        assertTrue(validation.validate(OffsetDateTime.now().minusHours(1)).valid());
    }

    @Test
    void test_invalid_isPastOffsetDateTime() {
        var validation = validationFactory.isPastOffsetDateTime("message");
        assertFalse(validation.validate(OffsetDateTime.now().plusHours(1)).valid());
    }

}
