package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.mock.UserMock;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_isNotNull() {
        var validation = validationFactory.isNotNull("message");
        var result = validation.validate("value");
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_isNotNull() {
        var validation = validationFactory.isNotNull("message");
        var result = validation.validate(null);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_isNull() {
        var validation = validationFactory.isNull("message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_isNull() {
        var validation = validationFactory.isNull("message");
        var result = validation.validate("value");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_isClass() {
        Object obj = UserMock.johnDoe();
        var validation = validationFactory.isClass(UserMock.class, "message");
        var result = validation.validate(obj);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_isClass() {
        Object obj = UserMock.johnDoe();
        var validation = validationFactory.isClass(String.class, "message");
        var result = validation.validate(obj);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_oneOf() {
        var validation = validationFactory.oneOf(Set.of("A", "B"), "message");
        var result = validation.validate("A");
        assertTrue(result.valid());
    }

    @Test
    void test_valid_oneOf_null() {
        var validation = validationFactory.oneOf(Set.of("A", "B"), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_oneOf() {
        var validation = validationFactory.oneOf(Set.of("A", "B"), "message");
        var result = validation.validate("C");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_custom() {
        var validation = validationFactory.<String>custom(value -> value != null && value.startsWith("SF-"), "message");
        var result = validation.validate("SF-123");
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_custom() {
        var validation = validationFactory.<String>custom(value -> value != null && value.startsWith("SF-"), "message");
        var result = validation.validate("XX-123");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

}
