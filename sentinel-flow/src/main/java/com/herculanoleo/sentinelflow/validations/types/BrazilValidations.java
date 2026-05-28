package com.herculanoleo.sentinelflow.validations.types;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import com.herculanoleo.sentinelflow.validations.Validation;
import com.herculanoleo.sentinelflow.validations.ValidationSupport;
import org.apache.commons.lang3.StringUtils;

/**
 * Validation rules for Brazilian document and phone formats.
 */
public interface BrazilValidations extends ValidationSupport {

    /** Validates a CPF number. */
    default Validation<String> cpf(String message) {
        return value -> {
            if (StringUtils.isNotBlank(value)) {
                try {
                    new CPFValidator().assertValid(value);
                } catch (Exception e) {
                    return invalid(message);
                }
            }
            return valid();
        };
    }

    /** Validates a CNPJ number. */
    default Validation<String> cnpj(String message) {
        return value -> {
            if (StringUtils.isNotBlank(value)) {
                try {
                    new CNPJValidator().assertValid(value);
                } catch (Exception e) {
                    return invalid(message);
                }
            }
            return valid();
        };
    }

    /**
     * Validates a CEP (8 digits).
     *
     * <p>Non-digit characters are ignored, so masked values such as {@code 12345-678} are accepted.
     */
    default Validation<String> cep(String message) {
        return value -> {
            if (StringUtils.isNotBlank(value)) {
                String digits = value.replaceAll("\\D", "");
                if (digits.length() != 8) {
                    return invalid(message);
                }
            }
            return valid();
        };
    }

    /** Validates a Brazilian phone number (10 or 11 digits with valid DDD). */
    default Validation<String> phoneBr(String message) {
        return value -> {
            if (StringUtils.isNotBlank(value)) {
                String digits = value.replaceAll("\\D", "");
                if (!BrazilValidationUtils.isValidPhoneBr(digits)) {
                    return invalid(message);
                }
            }
            return valid();
        };
    }

}
