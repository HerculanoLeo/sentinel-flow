package com.herculanoleo.sentinelflow.validations.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BrazilValidationsTest extends AbstractValidationTest {

    @Test
    void test_valid_cpf() {
        var validation = validationFactory.cpf("message");
        var result = validation.validate("61764923006");
        assertTrue(result.valid());
    }

    @Test
    void test_valid_cpf_null() {
        var validation = validationFactory.cpf("message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_cpf_length() {
        var validation = validationFactory.cpf("message");
        var result = validation.validate("6176492300");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_invalid_cpf() {
        var validation = validationFactory.cpf("message");
        var result = validation.validate("00000000000");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_cnpj() {
        var validation = validationFactory.cnpj("message");
        var result = validation.validate("47512416000152");
        assertTrue(result.valid());
    }

    @Test
    void test_valid_cnpj_null() {
        var validation = validationFactory.cnpj("message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_cnpj_length() {
        var validation = validationFactory.cnpj("message");
        var result = validation.validate("4751241600015");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_invalid_cnpj() {
        var validation = validationFactory.cnpj("message");
        var result = validation.validate("00000000000000");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_cep() {
        var validation = validationFactory.cep("message");
        var result = validation.validate("12312312");
        assertTrue(result.valid());
    }

    @Test
    void test_valid_cep_null() {
        var validation = validationFactory.cep("message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    void test_invalid_cep_length() {
        var validation = validationFactory.cep("message");
        var result = validation.validate("1231231");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    void test_valid_cep_with_mask() {
        var validation = validationFactory.cep("message");
        assertTrue(validation.validate("12345-678").valid());
    }

    @Test
    void test_valid_phoneBr_landline() {
        assertTrue(validationFactory.phoneBr("message").validate("(11) 3333-4444").valid());
    }

    @Test
    void test_valid_phoneBr_mobile() {
        assertTrue(validationFactory.phoneBr("message").validate("(11) 98888-7777").valid());
    }

    @Test
    void test_invalid_phoneBr() {
        assertFalse(validationFactory.phoneBr("message").validate("123").valid());
    }

    @Test
    void test_valid_phoneBr_null() {
        assertTrue(validationFactory.phoneBr("message").validate(null).valid());
    }

}
