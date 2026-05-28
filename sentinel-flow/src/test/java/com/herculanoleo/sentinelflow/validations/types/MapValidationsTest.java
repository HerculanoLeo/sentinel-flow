package com.herculanoleo.sentinelflow.validations.types;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_mapSizeEq() {
        var validation = validationFactory.mapSizeEq(2, "message");
        assertTrue(validation.validate(Map.of("a", 1, "b", 2)).valid());
    }

    @Test
    void test_invalid_mapSizeEq() {
        var validation = validationFactory.mapSizeEq(2, "message");
        assertFalse(validation.validate(Map.of("a", 1)).valid());
    }

    @Test
    void test_valid_mapSizeMin() {
        var validation = validationFactory.mapSizeMin(2, "message");
        assertTrue(validation.validate(Map.of("a", 1, "b", 2, "c", 3)).valid());
    }

    @Test
    void test_invalid_mapSizeMin() {
        assertFalse(validationFactory.mapSizeMin(2, "message").validate(Map.of("a", 1)).valid());
    }

    @Test
    void test_valid_mapSizeMax() {
        var validation = validationFactory.mapSizeMax(2, "message");
        assertTrue(validation.validate(Map.of("a", 1)).valid());
    }

    @Test
    void test_invalid_mapSizeMax() {
        assertFalse(validationFactory.mapSizeMax(2, "message")
                .validate(Map.of("a", 1, "b", 2, "c", 3)).valid());
    }

}
