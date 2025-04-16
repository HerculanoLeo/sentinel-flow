# SentinelFlow

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) 

A **functional** and **fluent** validation library for Java objects. `SentinelFlow` provides a declarative and concise way to define and execute complex validation rules, leveraging the power of modern Java's functional interfaces and lambda expressions.

## Objective

The main goal of this library is to offer an alternative or complement to traditional validation approaches (like Bean Validation), especially useful for:

* Complex business rules that are difficult to express with annotations.
* Conditional validations or those dependent on multiple fields.
* Scenarios where validation logic needs to be built dynamically.
* Keeping validation logic decoupled from models (POJOs/DTOs).

It promotes a more readable and expressive coding style for validations through its fluent API.

## Key Features

* **Functional Approach:** Defines validation rules as functions (lambdas), making the logic clear and concise.
* **Fluent API:** Chain validations naturally and legibly, improving code expressiveness.
* **Rich Set of Validations:** Includes common validations for Strings, Numbers, Dates, Objects, and specific Brazilian formats (CPF, CNPJ, CEP).
* **Customizable Error Messages:** Easy configuration of meaningful error messages for each validation rule.
* **Type-Safe:** Validations are applied to specific types, leveraging Java's type safety to prevent runtime errors.
* **Extensible:** Allows easy creation of new custom validation rules, adapting the library to your project's specific needs.
* **Decoupled:** Does not require your models (POJOs/DTOs) to be annotated or implement library-specific interfaces, keeping your domain clean.
* **Testable:** The functional nature and separation of concerns make it easy to write unit tests for both validation rules and the logic that uses them.

## Installation

Requires **Java 17** or higher.

Add the dependency to your project.

**Maven:**

```xml
<dependency> 
    <groupId>com.herculanoleo</groupId> 
    <artifactId>sentinel-flow</artifactId> 
    <version>X.Y.Z</version> 
</dependency>
```

**Gradle:**
```groovy
implementation 'com.herculanoleo:sentinel-flow:X.Y.Z'
```
*(Note: Replace X.Y.Z with the latest library version.)*

## How to Use (Traditional Java Application)

Validation is performed through the `Validate` interface, which is a `@FunctionalInterface`. You implement the validation logic using the `ValidatorFactory` and `ValidationFactory` factories.n an application without a dependency injection framework, you can instantiate the default implementations directly.

**Basic Example:**

Suppose you have a class `UserRegistrationDTO`:

```java
public record UserRegistrationDTO(String name, String email, String password, int age) {}
```

You can define and execute the validation as follows:

```java
import com.herculanoleo.sentinelflow.validator.Validate;
import com.herculanoleo.sentinelflow.validator.ValidatorFactory;
import com.herculanoleo.sentinelflow.validations.ValidationFactory;
import com.herculanoleo.sentinelflow.exceptions.ValidatorException;
// Import the default implementations
import com.herculanoleo.sentinelflow.validator.impl.ValidatorFactoryImpl;
import com.herculanoleo.sentinelflow.validations.impl.ValidationFactoryImpl;

public class UserRegistrationValidator {

    // Instantiate the factories (in larger apps, consider a central location for this)
    private final ValidatorFactory validatorFactory = new ValidatorFactoryImpl();

    private final ValidationFactory validationFactory = new ValidationFactoryImpl();

    public void validateUser(UserRegistrationDTO dto) throws ValidatorException {
        // Create the validation logic as a lambda
        Validate validationLogic = (validator, validations) -> {
            validator.create(dto)
                    .field("name", UserRegistrationDTO::name) // Define the field to be validated
                    .add(validations.isNotBlank("Name cannot be blank.")) // Add rules
                    .add(validations.lengthMin(3, "Name must have at least 3 characters."))
                    .end() // Finish definition for this field
                    .field("email", UserRegistrationDTO::email)
                    .add(validations.isNotBlank("Email cannot be blank."))
                    .add(validations.email("Invalid email format."))
                    .end()
                    .field("password", UserRegistrationDTO::password)
                    .add(validations.isNotBlank("Password cannot be blank."))
                    .add(validations.lengthMin(8, "Password must have at least 8 characters."))
                    .end()
                    .field("age", UserRegistrationDTO::age)
                    .add(validations.min(18, "Minimum age is 18."))
                    .end()
                    .validate(); // Execute all defined validations
        };
        // Execute the validation passing the factory instances
        try {
            validationLogic.validate(this.validatorFactory, this.validationFactory);
            System.out.println("DTO validation successful!");
        } catch (ValidatorException e) {
            System.err.println("DTO validation failed:");
            e.getErrors().forEach(error ->
                    System.err.println("- Field: " + error.fieldName() + ", Error: " + error.message())
            );
            // Re-throw or handle the exception according to your application's needs
            throw e;
        }
    }

    // Example of how to call the validation
    public static void main(String[] args) {
        // DTO with valid data
        UserRegistrationDTO validUser = new UserRegistrationDTO("John Doe", "john.doe@test.com", "password123", 30);
        // DTO with invalid data
        UserRegistrationDTO invalidUser = new UserRegistrationDTO("Jo", "invalid-email", "pass", 17);
        UserRegistrationValidator validator = new UserRegistrationValidator();
        System.out.println("--- Validating valid user ---");
        try {
            validator.validateUser(validUser);
        } catch (ValidatorException e) {
            // Not expected in this case
            System.err.println("Unexpected error validating valid user: " + e.getMessage());
        }
        System.out.println("\n--- Validating invalid user ---");
        try {
            validator.validateUser(invalidUser);
        } catch (ValidatorException e) {
            // Expected error, messages were already printed within the validateUser method
            System.out.println("Invalid user validation failed as expected.");
        }
    }
}
```
**Key Steps (Recap):**

1.  **Instantiate Factories:** Create instances of `ValidatorFactoryImpl` and `ValidationFactoryImpl`.
2.  **Define Logic:** Create an instance of `Validate` (usually a lambda or a class that implements the interface) that takes the factories as parameters.
3.  **Create Validator:** Inside the lambda, use `validatorFactory.create(objectToValidate)`.
4.  **Define Fields and Rules:** Use `.field("fieldName", fieldValue)` followed by `.add(validationRule)` and finish with `.end()`. The API is fluent, allowing chaining of multiple `.add()` for the same field.
5.  **Execute:** Call `.validate()` at the end of the `Validator` chain.
6.  **Call and Handle Exception:** Execute the lambda `validationLogic.validate(validatorFactoryInstance, validationFactoryInstance)` and be prepared to catch `ValidatorException` in case of failure.

This example shows basic usage in a scenario without complex frameworks, focusing on direct instantiation and execution of the validation logic.

## Spring Boot Integration

`SentinelFlow` can be easily integrated into Spring Boot applications. The `ValidatorFactory` and `ValidationFactory` factories can be declared as Spring-managed Beans, facilitating injection wherever needed.

**1. Bean Configuration:**

Create a configuration class to register the factory implementations in the Spring context.

```java
import com.herculanoleo.sentinelflow.validator.ValidatorFactory;
import com.herculanoleo.sentinelflow.validator.impl.ValidatorFactoryImpl;
import com.herculanoleo.sentinelflow.validations.ValidationFactory;
import com.herculanoleo.sentinelflow.validations.impl.ValidationFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {

    @Bean
    public ValidationFactory validationFactory() {
        // Returns the default implementation of the rule factory
        return new ValidationFactoryImpl();
    }

    @Bean
    public ValidatorFactory validatorFactory() {
        // Returns the default implementation of the validator factory
        // Note: If ValidatorFactoryImpl needs other dependencies in the future,
        // Spring can inject them automatically if they are also beans.
        return new ValidatorFactoryImpl();
    }
}
```
**2. Usage in a Component (e.g., Service):**

Now you can inject the factories into your services or other Spring components and use the `Validate` interface to define and execute the validation logic.

```java 
import com.herculanoleo.sentinelflow.validator.Validate;
import com.herculanoleo.sentinelflow.validator.ValidatorFactory;
import com.herculanoleo.sentinelflow.validations.ValidationFactory;
import com.herculanoleo.sentinelflow.exceptions.ValidatorException;
import org.springframework.stereotype.Service;
// Assuming UserRegistrationDTO exists
import com.yourpackage.dto.UserRegistrationDTO;

@Service
public class UserService {

    private final ValidatorFactory validatorFactory;
    
    private final ValidationFactory validationFactory;

    // Constructor dependency injection (recommended)
    public UserService(ValidatorFactory validatorFactory, ValidationFactory validationFactory) {
        this.validatorFactory = validatorFactory;
        this.validationFactory = validationFactory;
    }

    public void registerUser(UserRegistrationDTO dto) {
        // Define the validation logic specific to this method/use case
        // The lambda receives the factories that will be used internally.
        Validate userValidationLogic = (validator, validations) -> {
            validator.create(dto) // Uses the injected factory
                    .field("name", UserRegistrationDTO::name)
                    .add(validations.isNotBlank("Name is required.")) // Uses the injected factory
                    .add(validations.lengthMin(3, "Name too short."))
                    .end()
                    .field("email", UserRegistrationDTO::email)
                    .add(validations.isNotBlank("Email is required."))
                    .add(validations.email("Invalid email format."))
                    .end()
                    .field("age", UserRegistrationDTO::age)
                    .add(validations.min(18, "Must be of legal age."))
                    .end()
                    // ... other complex or business validations
                    .validate(); // Executes the validation
        };
        try {
            // Execute the validation using the injected beans
            userValidationLogic.validate(this.validatorFactory, this.validationFactory);
            // If it reached here, validation passed. Proceed with business logic.
            System.out.println("Valid user, registering...");
            // ... save user to database, call other services, etc.
        } catch (ValidatorException e) {
            // Logic to handle validation failures
            // Ex: Log errors, throw an application-specific exception (e.g., BadRequestException)
            // that can be handled globally by a @ControllerAdvice to return a 400 Bad Request.
            System.err.println("Validation error registering user: " + e.getErrors());
            // It's good practice to wrap ValidatorException in an exception from your application layer.
            throw new IllegalArgumentException("Invalid registration data: " + e.getErrors());
            // Or a custom exception: throw new BusinessValidationException(e.getErrors());
        }
    }
}
```

**Key Advantages with Spring:**

*   **Dependency Injection:** Spring manages the lifecycle of the factories.
*   **Testability:** You can easily mock `ValidatorFactory` and `ValidationFactory` in unit tests for your services.
*   **Consistency:** Ensures the same configured instance of the factories is used throughout the application.

This example demonstrates how the library integrates seamlessly with the Spring Boot ecosystem, allowing you to define functional validations cleanly and decoupled within your managed components.