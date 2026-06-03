package com.herculanoleo.sentinelflow.validations.types;

final class BrazilValidationUtils {

    private BrazilValidationUtils() {
    }

    static boolean isValidPhoneBr(String digits) {
        if (digits.length() != 10 && digits.length() != 11) {
            return false;
        }

        int ddd = Integer.parseInt(digits.substring(0, 2));
        if (ddd < 11 || ddd > 99) {
            return false;
        }

        if (digits.length() == 11) {
            return digits.charAt(2) == '9';
        }

        return true;
    }

}
