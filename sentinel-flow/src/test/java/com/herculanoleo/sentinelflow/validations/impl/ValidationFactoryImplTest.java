package com.herculanoleo.sentinelflow.validations.impl;

import com.herculanoleo.sentinelflow.mock.UserMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
public class ValidationFactoryImplTest {

    private ValidationFactoryImpl validationFactory;

    @BeforeEach
    public void setup() {
        this.validationFactory = spy(new ValidationFactoryImpl());
    }

    @Test
    public void test_valid_isNotNull() {
        var validation = validationFactory.isNotNull("message");
        var result = validation.validate("value");
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_isNotNull() {
        var validation = validationFactory.isNotNull("message");
        var result = validation.validate(null);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_isNull() {
        var validation = validationFactory.isNull("message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_isNull() {
        var validation = validationFactory.isNull("message");
        var result = validation.validate("value");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_isClass() {
        Object obj = UserMock.johnDoe();
        var validation = validationFactory.isClass("message", UserMock.class);
        var result = validation.validate(obj);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_isClass() {
        Object obj = UserMock.johnDoe();
        var validation = validationFactory.isClass("message", String.class);
        var result = validation.validate(obj);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_isNotBlank() {
        var validation = validationFactory.isNotBlank("message");
        var result = validation.validate(UserMock.johnDoe().name());
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_isNotBlank() {
        var validation = validationFactory.isNotBlank("message");
        var result = validation.validate(null);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_isBlank() {
        var validation = validationFactory.isBlank("message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_isBlank() {
        var validation = validationFactory.isBlank("message");
        var result = validation.validate(UserMock.johnDoe().name());
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_lengthEq() {
        var validation = validationFactory.lengthEq(5, "message");
        var result = validation.validate("12345");
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_lengthEq() {
        var validation = validationFactory.lengthEq(5, "message");
        var result = validation.validate("1234");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_lengthMin() {
        var validation = validationFactory.lengthMin(5, "message");
        var result = validation.validate("12345");
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_lengthMin() {
        var validation = validationFactory.lengthMin(5, "message");
        var result = validation.validate("1234");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_lengthMax() {
        var validation = validationFactory.lengthMax(5, "message");
        var result = validation.validate("12345");
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_lengthMax() {
        var validation = validationFactory.lengthMax(5, "message");
        var result = validation.validate("123456");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_email() {
        var validation = validationFactory.email("message");
        var result = validation.validate("test@example.com");
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_null_email() {
        var validation = validationFactory.email("message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_email() {
        var validation = validationFactory.email("message");
        var result = validation.validate("test@");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_onlyNumbers() {
        var validation = validationFactory.onlyNumbers("message");
        var result = validation.validate("12345");
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_onlyNumbers() {
        var validation = validationFactory.onlyNumbers("message");
        var result = validation.validate("12345D");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_Integer_eq() {
        var validation = validationFactory.eq(1, "message");
        var result = validation.validate(1);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_Integer_eq() {
        var validation = validationFactory.eq(1, "message");
        var result = validation.validate(2);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_Integer_min() {
        var validation = validationFactory.min(2, "message");
        var result = validation.validate(2);
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_Integer_min_null() {
        var validation = validationFactory.min(2, "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_Integer_min() {
        var validation = validationFactory.min(2, "message");
        var result = validation.validate(1);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_Integer_max() {
        var validation = validationFactory.max(2, "message");
        var result = validation.validate(2);
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_Integer_max_null() {
        var validation = validationFactory.max(2, "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_Integer_max() {
        var validation = validationFactory.max(1, "message");
        var result = validation.validate(2);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_Long_eq() {
        var validation = validationFactory.eq(1L, "message");
        var result = validation.validate(1L);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_Long_eq() {
        var validation = validationFactory.eq(1L, "message");
        var result = validation.validate(2L);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_Long_min() {
        var validation = validationFactory.min(2L, "message");
        var result = validation.validate(2L);
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_Long_min_null() {
        var validation = validationFactory.min(2L, "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_Long_min() {
        var validation = validationFactory.min(2L, "message");
        var result = validation.validate(1L);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_Long_max() {
        var validation = validationFactory.max(2L, "message");
        var result = validation.validate(2L);
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_Long_max_null() {
        var validation = validationFactory.max(2L, "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_Long_max() {
        var validation = validationFactory.max(1L, "message");
        var result = validation.validate(2L);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_Double_eq() {
        var validation = validationFactory.eq(1.0, "message");
        var result = validation.validate(1.0);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_Double_eq() {
        var validation = validationFactory.eq(1.0, "message");
        var result = validation.validate(2.0);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_Double_min() {
        var validation = validationFactory.min(2.0, "message");
        var result = validation.validate(2.0);
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_Double_min_null() {
        var validation = validationFactory.min(2.0, "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_Double_min() {
        var validation = validationFactory.min(2.0, "message");
        var result = validation.validate(1.0);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_Double_max() {
        var validation = validationFactory.max(2.0, "message");
        var result = validation.validate(2.0);
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_Double_max_null() {
        var validation = validationFactory.max(2.0, "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_Double_max() {
        var validation = validationFactory.max(1.0, "message");
        var result = validation.validate(2.0);
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_BigDecimal_eq() {
        var validation = validationFactory.eq(BigDecimal.valueOf(1.0), "message");
        var result = validation.validate(BigDecimal.valueOf(1.0));
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_BigDecimal_eq() {
        var validation = validationFactory.eq(BigDecimal.valueOf(1.0), "message");
        var result = validation.validate(BigDecimal.valueOf(2.0));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_BigDecimal_min() {
        var validation = validationFactory.min(BigDecimal.valueOf(2.0), "message");
        var result = validation.validate(BigDecimal.valueOf(2.0));
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_BigDecimal_min_null() {
        var validation = validationFactory.min(BigDecimal.valueOf(2.0), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_BigDecimal_min() {
        var validation = validationFactory.min(BigDecimal.valueOf(2.0), "message");
        var result = validation.validate(BigDecimal.valueOf(1.0));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_BigDecimal_max() {
        var validation = validationFactory.max(BigDecimal.valueOf(2.0), "message");
        var result = validation.validate(BigDecimal.valueOf(2.0));
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_BigDecimal_max_null() {
        var validation = validationFactory.max(BigDecimal.valueOf(2.0), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_BigDecimal_max() {
        var validation = validationFactory.max(BigDecimal.valueOf(1.0), "message");
        var result = validation.validate(BigDecimal.valueOf(2.0));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_LocalDate_eq() {
        var validation = validationFactory.eq(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(LocalDate.of(2025, 3, 31));
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_LocalDate_eq() {
        var validation = validationFactory.eq(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(LocalDate.of(2025, 3, 30));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_LocalDate_min() {
        var validation = validationFactory.min(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(LocalDate.of(2025, 3, 31));
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_LocalDate_min_null() {
        var validation = validationFactory.min(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_LocalDate_min() {
        var validation = validationFactory.min(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(LocalDate.of(2025, 3, 30));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_LocalDate_max() {
        var validation = validationFactory.max(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(LocalDate.of(2025, 3, 31));
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_LocalDate_max_null() {
        var validation = validationFactory.max(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_LocalDate_max() {
        var validation = validationFactory.max(LocalDate.of(2025, 3, 31), "message");
        var result = validation.validate(LocalDate.of(2025, 4, 1));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_LocalTime_eq() {
        var validation = validationFactory.eq(LocalTime.of(9, 30), "message");
        var result = validation.validate(LocalTime.of(9, 30));
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_LocalTime_eq() {
        var validation = validationFactory.eq(LocalTime.of(9, 30), "message");
        var result = validation.validate(LocalTime.of(9, 31));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_LocalTime_min() {
        var validation = validationFactory.min(LocalTime.of(9, 30), "message");
        var result = validation.validate(LocalTime.of(9, 30));
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_LocalTime_min_null() {
        var validation = validationFactory.min(LocalTime.of(9, 30), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_LocalTime_min() {
        var validation = validationFactory.min(LocalTime.of(9, 30), "message");
        var result = validation.validate(LocalTime.of(9, 29));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_LocalTime_max() {
        var validation = validationFactory.max(LocalTime.of(9, 30), "message");
        var result = validation.validate(LocalTime.of(9, 30));
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_LocalTime_max_null() {
        var validation = validationFactory.max(LocalTime.of(9, 30), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_LocalTime_max() {
        var validation = validationFactory.max(LocalTime.of(9, 30), "message");
        var result = validation.validate(LocalTime.of(9, 31));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_LocalDateTime_eq() {
        var validation = validationFactory.eq(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(LocalDateTime.of(2025, 3, 31, 9, 30));
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_LocalDateTime_eq() {
        var validation = validationFactory.eq(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(LocalDateTime.of(2025, 3, 31, 9, 31));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_LocalDateTime_min() {
        var validation = validationFactory.min(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(LocalDateTime.of(2025, 3, 31, 9, 30));
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_LocalDateTime_min_null() {
        var validation = validationFactory.min(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_LocalDateTime_min() {
        var validation = validationFactory.min(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(LocalDateTime.of(2025, 3, 31, 9, 29));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_LocalDateTime_max() {
        var validation = validationFactory.max(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(LocalDateTime.of(2025, 3, 31, 9, 30));
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_LocalDateTime_max_null() {
        var validation = validationFactory.max(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_LocalDateTime_max() {
        var validation = validationFactory.max(LocalDateTime.of(2025, 3, 31, 9, 30), "message");
        var result = validation.validate(LocalDateTime.of(2025, 3, 31, 9, 31));
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_cpf() {
        var validation = validationFactory.cpf("message");
        var result = validation.validate("61764923006");
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_cpf_null() {
        var validation = validationFactory.cpf("message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_cpf_length() {
        var validation = validationFactory.cpf("message");
        var result = validation.validate("6176492300");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_invalid_cpf() {
        var validation = validationFactory.cpf("message");
        var result = validation.validate("00000000000");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_cnpj() {
        var validation = validationFactory.cnpj("message");
        var result = validation.validate("47512416000152");
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_cnpj_null() {
        var validation = validationFactory.cnpj("message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_cnpj_length() {
        var validation = validationFactory.cnpj("message");
        var result = validation.validate("4751241600015");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_invalid_cnpj() {
        var validation = validationFactory.cnpj("message");
        var result = validation.validate("00000000000000");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

    @Test
    public void test_valid_cep() {
        var validation = validationFactory.cep("message");
        var result = validation.validate("12312312");
        assertTrue(result.valid());
    }

    @Test
    public void test_valid_cep_null() {
        var validation = validationFactory.cep("message");
        var result = validation.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void test_invalid_cep_length() {
        var validation = validationFactory.cep("message");
        var result = validation.validate("1231231");
        assertFalse(result.valid());
        assertEquals("message", result.message());
    }

}
