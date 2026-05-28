package com.herculanoleo.sentinelflow.validations.types;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollectionValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_sizeEq() {
        var validation = validationFactory.sizeEq(2, "message");
        var result = validation.validate(List.of("a", "b"));
        assertTrue(result.valid());
    }

    @Test
    void test_valid_sizeEq_null() {
        var validation = validationFactory.sizeEq(2, "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_sizeEq() {
        var validation = validationFactory.sizeEq(2, "message");
        var result = validation.validate(List.of("a"));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_sizeMin() {
        var validation = validationFactory.sizeMin(2, "message");
        var result = validation.validate(List.of("a", "b", "c"));
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_sizeMin() {
        var validation = validationFactory.sizeMin(2, "message");
        var result = validation.validate(List.of("a"));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_sizeMax() {
        var validation = validationFactory.sizeMax(2, "message");
        var result = validation.validate(List.of("a"));
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_sizeMax() {
        var validation = validationFactory.sizeMax(2, "message");
        var result = validation.validate(List.of("a", "b", "c"));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_isEmpty() {
        var validation = validationFactory.isEmpty("message");
        var result = validation.validate(List.of());
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_isEmpty() {
        var validation = validationFactory.isEmpty("message");
        var result = validation.validate(List.of("a"));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_isNotEmpty() {
        var validation = validationFactory.isNotEmpty("message");
        var result = validation.validate(List.of("a"));
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_isNotEmpty() {
        var validation = validationFactory.isNotEmpty("message");
        var result = validation.validate(List.of());
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_contains() {
        var validation = validationFactory.contains("admin", "message");
        var result = validation.validate(List.of("admin", "user"));
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_contains() {
        var validation = validationFactory.contains("admin", "message");
        var result = validation.validate(List.of("user"));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

}
