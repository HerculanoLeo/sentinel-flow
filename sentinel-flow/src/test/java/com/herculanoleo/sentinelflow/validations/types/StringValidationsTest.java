package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.mock.UserMock;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_isNotBlank() {
        var validation = validationFactory.isNotBlank("message");
        var result = validation.validate(UserMock.johnDoe().name());
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_isNotBlank() {
        var validation = validationFactory.isNotBlank("message");
        var result = validation.validate(null);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_isBlank() {
        var validation = validationFactory.isBlank("message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_isBlank() {
        var validation = validationFactory.isBlank("message");
        var result = validation.validate(UserMock.johnDoe().name());
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_lengthEq() {
        var validation = validationFactory.lengthEq(5, "message");
        var result = validation.validate("12345");
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_lengthEq() {
        var validation = validationFactory.lengthEq(5, "message");
        var result = validation.validate("1234");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_lengthMin() {
        var validation = validationFactory.lengthMin(5, "message");
        var result = validation.validate("12345");
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_lengthMin() {
        var validation = validationFactory.lengthMin(5, "message");
        var result = validation.validate("1234");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_lengthMax() {
        var validation = validationFactory.lengthMax(5, "message");
        var result = validation.validate("12345");
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_lengthMax() {
        var validation = validationFactory.lengthMax(5, "message");
        var result = validation.validate("123456");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_email() {
        var validation = validationFactory.email("message");
        var result = validation.validate("test@example.com");
        assertTrue(result.valid());
    }

    @Test
    void test_valid_null_email() {
        var validation = validationFactory.email("message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_email() {
        var validation = validationFactory.email("message");
        var result = validation.validate("test@");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_onlyNumbers() {
        var validation = validationFactory.onlyNumbers("message");
        var result = validation.validate("12345");
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_onlyNumbers() {
        var validation = validationFactory.onlyNumbers("message");
        var result = validation.validate("12345D");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_url() {
        var validation = validationFactory.url("message");
        var result = validation.validate("https://example.com/path");
        assertTrue(result.valid());
    }

    @Test
    void test_valid_url_null() {
        var validation = validationFactory.url("message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_url() {
        var validation = validationFactory.url("message");
        var result = validation.validate("not-a-url");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_https() {
        var validation = validationFactory.https("message");
        var result = validation.validate("https://example.com");
        assertTrue(result.valid());
    }

    @Test
    void test_valid_https_null() {
        var validation = validationFactory.https("message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_https_scheme() {
        var validation = validationFactory.https("message");
        var result = validation.validate("http://example.com");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_fileExtension() {
        var validation = validationFactory.fileExtension(Set.of("pdf", ".png"), "message");
        var result = validation.validate("document.pdf");
        assertTrue(result.valid());
    }

    @Test
    void test_valid_fileExtension_null() {
        var validation = validationFactory.fileExtension(Set.of("pdf"), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_fileExtension() {
        var validation = validationFactory.fileExtension(Set.of("pdf"), "message");
        var result = validation.validate("document.txt");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_matches() {
        var validation = validationFactory.matches(Pattern.compile("^[A-Z]{3}$"), "message");
        var result = validation.validate("ABC");
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_matches() {
        var validation = validationFactory.matches(Pattern.compile("^[A-Z]{3}$"), "message");
        var result = validation.validate("AB");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_regex() {
        var validation = validationFactory.regex("^[A-Z]{3}$", "message");
        var result = validation.validate("ABC");
        assertTrue(result.valid());
    }

    @Test
    void test_valid_uuid() {
        var validation = validationFactory.uuid("message");
        var result = validation.validate("550e8400-e29b-41d4-a716-446655440000");
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_uuid() {
        var validation = validationFactory.uuid("message");
        var result = validation.validate("not-a-uuid");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_alpha() {
        var validation = validationFactory.alpha("message");
        var result = validation.validate("Abc");
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_alpha() {
        var validation = validationFactory.alpha("message");
        var result = validation.validate("Abc1");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_alphaNumeric() {
        var validation = validationFactory.alphaNumeric("message");
        var result = validation.validate("Abc123");
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_alphaNumeric() {
        var validation = validationFactory.alphaNumeric("message");
        var result = validation.validate("Abc-123");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_slug() {
        assertTrue(validationFactory.slug("message").validate("my-page-title").valid());
    }

    @Test
    void test_invalid_slug() {
        assertFalse(validationFactory.slug("message").validate("My Page").valid());
    }

    @Test
    void test_valid_ipAddress_v4() {
        assertTrue(validationFactory.ipAddress("message").validate("192.168.0.1").valid());
    }

    @Test
    void test_valid_ipAddress_v6() {
        assertTrue(validationFactory.ipAddress("message").validate("2001:db8::1").valid());
    }

    @Test
    void test_invalid_ipAddress() {
        assertFalse(validationFactory.ipAddress("message").validate("not-an-ip").valid());
    }

    @Test
    void test_valid_uuid_null() {
        assertTrue(validationFactory.uuid("message").validate(null).valid());
    }

    @Test
    void test_valid_ipAddress_null() {
        assertTrue(validationFactory.ipAddress("message").validate(null).valid());
    }

    @Test
    void test_invalid_fileExtension_without_extension() {
        assertFalse(validationFactory.fileExtension(Set.of("pdf"), "message").validate("document").valid());
    }

}
