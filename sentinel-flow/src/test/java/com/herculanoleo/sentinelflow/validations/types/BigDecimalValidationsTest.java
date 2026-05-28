package com.herculanoleo.sentinelflow.validations.types;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BigDecimalValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_eq() {
        var validation = validationFactory.eq(BigDecimal.valueOf(1.0), "message");
        var result = validation.validate(BigDecimal.valueOf(1.0));
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_eq() {
        var validation = validationFactory.eq(BigDecimal.valueOf(1.0), "message");
        var result = validation.validate(BigDecimal.valueOf(2.0));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_min() {
        var validation = validationFactory.min(BigDecimal.valueOf(2.0), "message");
        var result = validation.validate(BigDecimal.valueOf(2.0));
        assertTrue(result.valid());
    }

    @Test
    void test_valid_min_null() {
        var validation = validationFactory.min(BigDecimal.valueOf(2.0), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_min() {
        var validation = validationFactory.min(BigDecimal.valueOf(2.0), "message");
        var result = validation.validate(BigDecimal.valueOf(1.0));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_max() {
        var validation = validationFactory.max(BigDecimal.valueOf(2.0), "message");
        var result = validation.validate(BigDecimal.valueOf(2.0));
        assertTrue(result.valid());
    }

    @Test
    void test_valid_max_null() {
        var validation = validationFactory.max(BigDecimal.valueOf(2.0), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_max() {
        var validation = validationFactory.max(BigDecimal.valueOf(1.0), "message");
        var result = validation.validate(BigDecimal.valueOf(2.0));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_between() {
        var validation = validationFactory.between(BigDecimal.ONE, BigDecimal.TEN, "message");
        assertTrue(validation.validate(BigDecimal.valueOf(5)).valid());
    }

    @Test
    void test_invalid_between() {
        assertFalse(validationFactory.between(BigDecimal.ONE, BigDecimal.TEN, "message")
                .validate(BigDecimal.valueOf(11)).valid());
    }

    @Test
    void test_valid_gt() {
        assertTrue(validationFactory.gt(BigDecimal.ZERO, "message").validate(BigDecimal.ONE).valid());
    }

    @Test
    void test_invalid_gt() {
        assertFalse(validationFactory.gt(BigDecimal.ZERO, "message").validate(BigDecimal.ZERO).valid());
    }

    @Test
    void test_valid_lt() {
        assertTrue(validationFactory.lt(BigDecimal.ZERO, "message").validate(BigDecimal.valueOf(-1)).valid());
    }

    @Test
    void test_invalid_lt() {
        assertFalse(validationFactory.lt(BigDecimal.ZERO, "message").validate(BigDecimal.ONE).valid());
    }

}
