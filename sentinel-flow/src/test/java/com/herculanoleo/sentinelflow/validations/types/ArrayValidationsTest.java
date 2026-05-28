package com.herculanoleo.sentinelflow.validations.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_arraySizeEq() {
        var validation = validationFactory.arraySizeEq(2, "message");
        assertTrue(validation.validate(new String[]{"a", "b"}).valid());
    }

    @Test
    void test_invalid_arraySizeEq() {
        var validation = validationFactory.arraySizeEq(2, "message");
        assertFalse(validation.validate(new String[]{"a"}).valid());
    }

    @Test
    void test_valid_arraySizeMin() {
        var validation = validationFactory.arraySizeMin(2, "message");
        assertTrue(validation.validate(new String[]{"a", "b", "c"}).valid());
    }

    @Test
    void test_invalid_arraySizeMin() {
        assertFalse(validationFactory.arraySizeMin(2, "message").validate(new String[]{"a"}).valid());
    }

    @Test
    void test_valid_arraySizeMax() {
        var validation = validationFactory.arraySizeMax(2, "message");
        assertTrue(validation.validate(new String[]{"a"}).valid());
    }

    @Test
    void test_invalid_arraySizeMax() {
        assertFalse(validationFactory.arraySizeMax(2, "message")
                .validate(new String[]{"a", "b", "c"}).valid());
    }

}
