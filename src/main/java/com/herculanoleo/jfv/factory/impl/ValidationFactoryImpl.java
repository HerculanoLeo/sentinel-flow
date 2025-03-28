package com.herculanoleo.jfv.factory.impl;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import com.herculanoleo.jfv.factory.ValidationFactory;
import com.herculanoleo.jfv.models.ValidationResult;
import com.herculanoleo.jfv.validations.Validation;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.regex.Pattern;

public class ValidationFactoryImpl implements ValidationFactory {

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,}$");

    //Objects
    @Override
    public <T> Validation<T> isNotNull(String message) {
        return (value) -> {
            if (Objects.nonNull(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public <T> Validation<T> isNull(String message) {
        return (value) -> {
            if (Objects.isNull(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public <T> Validation<T> isClass(String message, Class<?> clazz) {
        return (value) -> {
            if (clazz.isAssignableFrom(value.getClass())) {
                return valid();
            }
            return invalid(message);
        };
    }

    //Strings
    @Override
    public Validation<String> isNotBlank(String message) {
        return value -> {
            if (StringUtils.isNotBlank(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<String> isBlank(String message) {
        return value -> {
            if (StringUtils.isBlank(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<String> lengthEq(Integer length, String message) {
        return (value) -> {
            if (StringUtils.defaultString(value).length() == length || StringUtils.defaultString(value).isEmpty()) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<String> lengthMin(Integer length, String message) {
        return (value) -> {
            if (StringUtils.defaultString(value).length() >= length || StringUtils.defaultString(value).isEmpty()) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<String> lengthMax(Integer length, String message) {
        return (value) -> {
            if (StringUtils.defaultString(value).length() <= length || StringUtils.defaultString(value).isEmpty()) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<String> email(String message) {
        return value -> {
            if (StringUtils.isBlank(value) || EMAIL_REGEX.matcher(value).matches()) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<String> onlyNumbers(String message) {
        return value -> {
            var original = StringUtils.defaultString(value);
            var normalized = StringUtils.defaultString(value).replaceAll("\\D", "");

            if (StringUtils.equals(original, normalized)) {
                return valid();
            }

            return invalid(message);
        };
    }

    //Integer
    @Override
    public Validation<Integer> eq(Integer eq, String message) {
        return value -> {
            if (Objects.equals(value, eq)) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<Integer> min(Integer min, String message) {
        return value -> {
            if (null == value || value >= min) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<Integer> max(Integer max, String message) {
        return value -> {
            if (null == value || value <= max) {
                return valid();
            }
            return invalid(message);
        };
    }

    //Long
    @Override
    public Validation<Long> eq(Long eq, String message) {
        return value -> {
            if (Objects.equals(value, eq)) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<Long> min(Long min, String message) {
        return value -> {
            if (null == value || value >= min) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<Long> max(Long max, String message) {
        return value -> {
            if (null == value || value <= max) {
                return valid();
            }
            return invalid(message);
        };
    }

    //Double
    @Override
    public Validation<Double> eq(Double eq, String message) {
        return value -> {
            if (Objects.equals(value, eq)) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<Double> min(Double min, String message) {
        return value -> {
            if (null == value || value >= min) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<Double> max(Double max, String message) {
        return value -> {
            if (null == value || value <= max) {
                return valid();
            }
            return invalid(message);
        };
    }

    //BigDecimal
    @Override
    public Validation<BigDecimal> eq(BigDecimal eq, String message) {
        return value -> {
            if (null != value && eq.compareTo(value) == 0) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<BigDecimal> min(BigDecimal min, String message) {
        return value -> {
            if (null == value || value.compareTo(min) >= 0) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<BigDecimal> max(BigDecimal max, String message) {
        return value -> {
            if (null == value || value.compareTo(max) <= 0) {
                return valid();
            }
            return invalid(message);
        };
    }

    //LocalDate
    @Override
    public Validation<LocalDate> eq(LocalDate eq, String message) {
        return value -> {
            if (null != value && eq.isEqual(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<LocalDate> min(LocalDate min, String message) {
        return value -> {
            if (null == value || value.isAfter(min) || value.isEqual(min)) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<LocalDate> max(LocalDate max, String message) {
        return value -> {
            if (null == value || value.isBefore(max) || value.isEqual(max)) {
                return valid();
            }
            return invalid(message);
        };
    }

    //LocalTime
    @Override
    public Validation<LocalTime> eq(LocalTime eq, String message) {
        return value -> {
            if (eq.equals(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<LocalTime> min(LocalTime min, String message) {
        return value -> {
            if (null == value || (value.isAfter(min) || value.equals(min))) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<LocalTime> max(LocalTime max, String message) {
        return value -> {
            if (null == value || (value.isBefore(max) || value.equals(max))) {
                return valid();
            }
            return invalid(message);
        };
    }

    //LocalDateTime
    @Override
    public Validation<LocalDateTime> eq(LocalDateTime eq, String message) {
        return value -> {
            if (null != value && eq.isEqual(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<LocalDateTime> min(LocalDateTime min, String message) {
        return value -> {
            if (null == value || value.isAfter(min) || value.isEqual(min)) {
                return valid();
            }
            return invalid(message);
        };
    }

    @Override
    public Validation<LocalDateTime> max(LocalDateTime max, String message) {
        return value -> {
            if (null == value || value.isBefore(max) || value.isEqual(max)) {
                return valid();
            }
            return invalid(message);
        };
    }

    // Brazil
    public Validation<String> cpf(String message) {
        return value -> {
            if (StringUtils.isNotBlank(value)) {
                String doc = value.replaceAll("\\D", "");

                if (doc.length() != 11) {
                    return invalid(message);
                }

                if (!new CPFValidator().isEligible(doc)) {
                    return invalid(message);
                }
            }
            return valid();
        };
    }

    public Validation<String> cnpj(String message) {
        return value -> {
            if (StringUtils.isNotBlank(value)) {
                String doc = value.replaceAll("\\D", "");

                if (doc.length() != 14) {
                    return invalid(message);
                }

                if (!new CNPJValidator().isEligible(doc)) {
                    return invalid(message);
                }
            }
            return valid();
        };
    }

    public Validation<String> cep(String message) {
        return value -> {
            if (StringUtils.isNotBlank(value)) {
                String doc = value.replaceAll("\\D", "");
                if (doc.length() != 8) {
                    return invalid(message);
                }
            }
            return valid();
        };
    }

    @Override
    public ValidationResult valid() {
        return new ValidationResult(true, null);
    }

    @Override
    public ValidationResult invalid(String message) {
        return new ValidationResult(false, message);
    }

}
