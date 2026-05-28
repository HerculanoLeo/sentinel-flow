package com.herculanoleo.sentinelflow.validations.types;

import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validations.ValidationSupport;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.regex.Pattern;

import static com.herculanoleo.sentinelflow.validations.types.StringValidationUtils.ALPHA_NUMERIC_REGEX;
import static com.herculanoleo.sentinelflow.validations.types.StringValidationUtils.ALPHA_REGEX;
import static com.herculanoleo.sentinelflow.validations.types.StringValidationUtils.EMAIL_REGEX;
import static com.herculanoleo.sentinelflow.validations.types.StringValidationUtils.SLUG_REGEX;

/**
 * Validation rules for {@link String} values.
 *
 * <p>Blank and {@code null} strings are considered valid for format validations
 * (email, url, uuid, etc.) unless the rule explicitly requires content.
 */
public interface StringValidations extends ValidationSupport {

    /**
     * Validates that the string is not blank.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> isNotBlank(String message) {
        return value -> {
            if (StringUtils.isNotBlank(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the string is blank or {@code null}.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> isBlank(String message) {
        return value -> {
            if (StringUtils.isBlank(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates the exact string length.
     *
     * @param length  expected length
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> lengthEq(Integer length, String message) {
        return value -> {
            if (StringUtils.defaultString(value).length() == length) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates the minimum string length.
     *
     * @param length  minimum length
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> lengthMin(Integer length, String message) {
        return value -> {
            if (StringUtils.defaultString(value).length() >= length) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates the maximum string length.
     *
     * @param length  maximum length
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> lengthMax(Integer length, String message) {
        return value -> {
            if (StringUtils.defaultString(value).length() <= length) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates a standard email format.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> email(String message) {
        return value -> {
            if (StringUtils.isBlank(value) || EMAIL_REGEX.matcher(value).matches()) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the string contains digits only.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> onlyNumbers(String message) {
        return value -> {
            var original = StringUtils.defaultString(value);
            var normalized = StringUtils.defaultString(value).replaceAll("\\D", "");

            if (StringUtils.equals(original, normalized)) {
                return valid();
            }

            return invalid(message);
        };
    }

    /**
     * Validates a URL with scheme and host.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> url(String message) {
        return value -> {
            if (StringUtils.isBlank(value) || StringValidationUtils.isValidUrl(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates an HTTPS URL.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> https(String message) {
        return value -> {
            if (StringUtils.isBlank(value) || StringValidationUtils.isValidHttps(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the file name has one of the allowed extensions.
     *
     * @param extensions allowed extensions (with or without leading dot)
     * @param message    error message when validation fails
     * @return validation rule
     */
    default Validation<String> fileExtension(Set<String> extensions, String message) {
        Set<String> allowed = StringValidationUtils.normalizeExtensions(extensions);

        return value -> {
            if (StringUtils.isBlank(value)) {
                return valid();
            }

            int dotIndex = value.lastIndexOf('.');
            if (dotIndex < 0 || dotIndex == value.length() - 1) {
                return invalid(message);
            }

            var extension = value.substring(dotIndex + 1).toLowerCase();
            if (allowed.contains(extension)) {
                return valid();
            }

            return invalid(message);
        };
    }

    /**
     * Validates that the string matches the given pattern.
     *
     * @param pattern compiled regex pattern
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> matches(Pattern pattern, String message) {
        return value -> {
            if (StringUtils.isBlank(value) || pattern.matcher(value).matches()) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates that the string matches the given regex.
     *
     * @param pattern regex pattern
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> regex(String pattern, String message) {
        return matches(Pattern.compile(pattern), message);
    }

    /**
     * Validates a UUID string.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> uuid(String message) {
        return value -> {
            if (StringUtils.isBlank(value) || StringValidationUtils.isValidUuid(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

    /**
     * Validates letters only (A-Z, a-z).
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> alpha(String message) {
        return matches(ALPHA_REGEX, message);
    }

    /**
     * Validates letters and digits only.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> alphaNumeric(String message) {
        return matches(ALPHA_NUMERIC_REGEX, message);
    }

    /**
     * Validates a kebab-case slug.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> slug(String message) {
        return matches(SLUG_REGEX, message);
    }

    /**
     * Validates an IPv4 or IPv6 address.
     *
     * @param message error message when validation fails
     * @return validation rule
     */
    default Validation<String> ipAddress(String message) {
        return value -> {
            if (StringUtils.isBlank(value) || StringValidationUtils.isValidIpAddress(value)) {
                return valid();
            }
            return invalid(message);
        };
    }

}
