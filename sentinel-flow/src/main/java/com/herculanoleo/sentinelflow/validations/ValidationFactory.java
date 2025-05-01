package com.herculanoleo.sentinelflow.validations;

import com.herculanoleo.sentinelflow.models.ValidationResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The `ValidationFactory` interface provides a set of methods for creating various types of validation rules.
 * It allows the creation of validators for different data types including objects, strings, numbers, dates, and specific Brazilian documents (CPF, CNPJ, CEP).
 */
public interface ValidationFactory {


    //Objects
    /**
     * Checks if the given value is not null and returns a valid result, otherwise returns an invalid result with the specified message.
     *
     * @param <T> The type of the value to be validated.
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the given value is not null.
     */
   <T> Validation<T> isNotNull(String message);

    /**
     * Checks if the given value is null and returns a valid result, otherwise returns an invalid result with the specified message.
     *
     * @param <T> The type of the value to be validated.
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the given value is null.
     */
   <T> Validation<T> isNull(String message);

    /**
     * Validates whether the given value is an instance of a specified class and returns either a valid or invalid result based on the check.
     *
     * @param <T> The type of the value to be validated.
     * @param message The error message to return when the validation fails.
     * @param clazz The class to which the value should be assignable from.
     * @return A Validation object that validates whether the given value is an instance of the specified class.
     */
   <T> Validation<T> isClass(String message, Class<?> clazz);

    /**
     * Checks if the given value is not blank and returns a valid result, otherwise returns an invalid result with the specified message.
     *
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the given value is not blank.
     */
    //Strings
   Validation<String> isNotBlank(String message);

    /**
     * Checks if the given value is blank and returns a valid result, otherwise returns an invalid result with the specified message.
     *
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the given value is blank.
     */
   Validation<String> isBlank(String message);

    /**
     * Checks if the length of the given string value is equal to a specified length and returns either a valid or invalid result based on the check.
     *
     * @param length The expected length of the string.
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the length of the given string value is equal to the specified length.
     */
   Validation<String> lengthEq(Integer length, String message);

    /**
     * Defines a method to validate the minimum length of a string value.
     *
     * @param length The expected minimum length of the string.
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the length of the given string value is at least the specified length.
     */
   Validation<String> lengthMin(Integer length, String message);

    /**
     * Defines a method to validate the maximum length of a string value.
     * @param length The expected maximum length of the string.
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the length of the given string value is at most the specified length.
     */
   Validation<String> lengthMax(Integer length, String message);

    /**
     * Defines a method to validate an email address based on the specified pattern and returns either a valid or invalid result.
     *
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the given value is a valid email according to the predefined regex pattern.
     */
   Validation<String> email(String message);

    /**
     * Defines a method to validate that the given string contains only numeric characters and returns either a valid or invalid result based on the check.
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the given value is a string containing only numeric characters.
     */
   Validation<String> onlyNumbers(String message);

    //Integer
    /**
     * Checks if the given value is equal to a specified integer and returns either a valid or invalid result based on the check.
     *
     * @param eq The integer value to compare with.
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the given value is equal to the specified integer.
     */
   Validation<Integer> eq(Integer eq, String message);

    /**
     * Defines a method to validate the minimum value of an integer and returns either a valid or invalid result based on the check.
     * @param min The expected minimum value.
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the given integer value is at least the specified minimum value.
     */
   Validation<Integer> min(Integer min, String message);

    /**
     * Defines a method to validate the maximum value of an integer and returns either a valid or invalid result based on the check.
     * @param max The expected maximum value.
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the given integer value is at most the specified maximum value.
     */
   Validation<Integer> max(Integer max, String message);

    //Long
    /**
     * Defines a method to validate that the given long value is equal to a specified long and returns either a valid or invalid result based on the check.
     *
     * @param eq The long value to compare with.
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the given value is equal to the specified long.
     */
   Validation<Long> eq(Long eq, String message);

    /**
     * Defines a method to validate the minimum value of a long and returns either a valid or invalid result based on the check.
     * @param min The expected minimum value.
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the given long value is at least the specified minimum value.
     */
   Validation<Long> min(Long min, String message);

    /**
     * Defines a method to validate the maximum value of a long and returns either a valid or invalid result based on the check.
     * @param max The expected maximum value.
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the given long value is at most the specified maximum value.
     */
   Validation<Long> max(Long max, String message);

    //Double
    /**
     * Defines a method to check if the given double value is equal to a specified double and returns either a valid or invalid result based on the check.
     *
     * @param eq The double value to compare with.
     * @param message The error message to return when the validation fails.
     * @return A Validation object that validates whether the given value is equal to the specified double.
     */
   Validation<Double> eq(Double eq, String message);

    /**
     * Creates a validation function that checks if the input value is greater than or equal to the specified minimum value.
     *
     * @param min The minimum acceptable value.
     * @param message The error message to return when the validation fails.
     * @return A validation function that returns valid if the value is not null and is greater than or equal to the minimum, otherwise invalid with the provided message.
     */
   Validation<Double> min(Double min, String message);

    /**
     * Defines a validation rule to check if the input value is less than or equal to a specified maximum value.
     *
     * @param max The maximum allowable value for the input.
     * @param message The error message to be returned when the validation fails.
     * @return A Validation object that encapsulates the validation logic.
     */
   Validation<Double> max(Double max, String message);

    //BigDecimal
    /**
     * Defines a validation method to check if the current value is equal to a given BigDecimal value.
     *
     * @param eq The BigDecimal value to compare with.
     * @param message The error message to be returned if the comparison fails.
     * @return A Validation object that either returns valid or invalid based on the comparison result.
     */
   Validation<BigDecimal> eq(BigDecimal eq, String message);

    /**
     * Specifies a minimum validation for a numeric value.
     *
     * @param min The minimum allowed value.
     * @param message The error message to be returned if the validation fails.
     * @return A Validation function that checks whether the input value meets the specified minimum condition.
     */
   Validation<BigDecimal> min(BigDecimal min, String message);

    /**
     * Defines a validation rule that checks if the input value is less than or equal to a specified maximum value.
     * @param max The maximum allowable value.
     * @param message The error message to be returned if the validation fails.
     * @return A validation function that returns valid if the value is within the allowed range, otherwise invalid with the provided message.
     */
   Validation<BigDecimal> max(BigDecimal max, String message);

    //LocalDate
    /**
     * Defines a validation rule to check if the input value is equal to a specified {@link LocalDate}.
     *
     * @param eq The date to compare against.
     * @param message The error message to return if the validation fails.
     * @return A Validation function that returns valid if the input matches the specified date, otherwise invalid with the given message.
     */
   Validation<LocalDate> eq(LocalDate eq, String message);

    /**
     * Defines a validation to check if the input date is after or equal to a specified minimum date.
     *
     * @param min The minimum LocalDate value that the input must be compared against.
     * @param message The error message to return if the validation fails.
     * @return A Validation object which will be valid if the input date is not null and is after or equal to the specified minimum date, otherwise invalid with the provided message.
     */
   Validation<LocalDate> min(LocalDate min, String message);

    /**
     * Sets a validation to ensure that the date is not after the specified maximum date.
     * @param max The maximum allowed date (inclusive).
     * @param message The error message to be returned if the validation fails.
     * @return A Validation function that checks whether the date is before or equal to the specified maximum date.
     */
   Validation<LocalDate> max(LocalDate max, String message);

    //LocalTime
    /**
     * Defines a validation method to check if the current value is equal to a specified LocalTime.
     *
     * @param eq The LocalTime value to compare with.
     * @param message The error message to return if the validation fails.
     * @return A Validation object that checks equality between the current value and the provided LocalTime.
     */
   Validation<LocalTime> eq(LocalTime eq, String message);

    /**
     * Creates a validation that checks if the input value is after or equal to the specified minimum time.
     * @param min The minimum allowed LocalTime value.
     * @param message The error message to be returned when the validation fails.
     * @return A Validation function that evaluates the given LocalTime against the specified minimum value.
     */
   Validation<LocalTime> min(LocalTime min, String message);

    /**
     * Sets a constraint to ensure the validated value is no later than a specified maximum time (inclusive).
     *
     * @param max   The maximum LocalTime value allowed.
     * @param message The error message to return if the validation fails.
     * @return A Validation function that checks whether the input value falls within the specified range.
     */
   Validation<LocalTime> max(LocalTime max, String message);

    //LocalDateTime
    /**
     * Checks if the current value is equal to a given LocalDateTime.
     * @param eq The LocalDateTime to compare with.
     * @param message The error message to be returned if the validation fails.
     * @return A Validation object that contains either a valid result or an invalid result based on whether the values are equal.
     */
   Validation<LocalDateTime> eq(LocalDateTime eq, String message);

    /**
     * Returns a validation function that checks if the input date time is after or equal to the specified minimum date time.
     *
     * @param min The minimum LocalDateTime value allowed for validation.
     * @param message The error message to be returned when the validation fails.
     * @return A Validation object representing the result of the validation check.
     */
   Validation<LocalDateTime> min(LocalDateTime min, String message);

    /**
     * Creates a validation constraint to check if the input date and time is before or equal to a specified maximum date and time.
     *
     * @param max The maximum LocalDateTime value allowed.
     * @param message The error message to be returned when the validation fails.
     * @return A Validation object that validates whether the input date and time is within the specified range.
     */
   Validation<LocalDateTime> max(LocalDateTime max, String message);

    //Long
    /**
     * Validates a Brazilian CPF number.
     *
     * @param message The error message to be returned if the validation fails.
     * @return A Validation object that is either valid or invalid based on the CPF's validity.
     */
    Validation<String> cpf(String message);

    /**
     * Creates a validation for checking if the given string is a valid CNPJ.
     * @param message The error message to be returned when validation fails.
     * @return A Validation object that either returns valid or invalid based on the CNPJ format and validity check.
     */
    Validation<String> cnpj(String message);

    /**
     * Validates a CEP (Postal Code) value.
     * @param message The error message to be returned if the validation fails.
     * @return A Validation object that is either valid or invalid based on the CEP format.
     */
    Validation<String> cep(String message);

    /**
     * Validates the current state and returns a result indicating whether the validation was successful.
     * @return A {@link ValidationResult} object containing true if the validation is successful, otherwise false, along with an error message (or null if no error).
     */
     ValidationResult valid();

    /**
     * Returns a {@link ValidationResult} indicating an invalid state with the given error message.
     *
     * @param message The error message describing why the validation failed.
     * @return A {@link ValidationResult} object where the valid flag is set to false and the message contains the provided error message.
     */
     ValidationResult invalid(String message);

}