package com.herculanoleo.sentinelflow.validations;

import com.herculanoleo.sentinelflow.models.ValidationResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface ValidationFactory {

    //Objects
    <T> Validation<T> isNotNull(String message);

    <T> Validation<T> isNull(String message);

    <T> Validation<T> isClass(String message, Class<?> clazz);

    //Strings
    Validation<String> isNotBlank(String message);

    Validation<String> isBlank(String message);

    Validation<String> lengthEq(Integer length, String message);

    Validation<String> lengthMin(Integer length, String message);

    Validation<String> lengthMax(Integer length, String message);

    Validation<String> email(String message);

    Validation<String> onlyNumbers(String message);

    //Integer
    Validation<Integer> eq(Integer eq, String message);

    Validation<Integer> min(Integer min, String message);

    Validation<Integer> max(Integer max, String message);

    //Long
    Validation<Long> eq(Long eq, String message);

    Validation<Long> min(Long min, String message);

    Validation<Long> max(Long max, String message);

    //Double
    Validation<Double> eq(Double eq, String message);

    Validation<Double> min(Double min, String message);

    Validation<Double> max(Double max, String message);

    //BigDecimal
    Validation<BigDecimal> eq(BigDecimal eq, String message);

    Validation<BigDecimal> min(BigDecimal min, String message);

    Validation<BigDecimal> max(BigDecimal max, String message);

    //LocalDate
    Validation<LocalDate> eq(LocalDate eq, String message);

    Validation<LocalDate> min(LocalDate min, String message);

    Validation<LocalDate> max(LocalDate max, String message);

    //LocalTime
    Validation<LocalTime> eq(LocalTime eq, String message);

    Validation<LocalTime> min(LocalTime min, String message);

    Validation<LocalTime> max(LocalTime max, String message);

    //LocalDateTime
    Validation<LocalDateTime> eq(LocalDateTime eq, String message);

    Validation<LocalDateTime> min(LocalDateTime min, String message);

    Validation<LocalDateTime> max(LocalDateTime max, String message);

    //Brazil
    Validation<String> cpf(String message);

    Validation<String> cnpj(String message);

    Validation<String> cep(String message);

    //Results
    ValidationResult valid();

    ValidationResult invalid(String message);

}
