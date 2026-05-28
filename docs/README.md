# SentinelFlow Documentation

Reference documentation for the validation library.

## Validations

All rules are exposed through `ValidationFactory` (default implementation: `ValidationFactoryImpl`).

| Category | Type | Methods |
|----------|------|---------|
| [Object](validations/object.md) | `<T>` | 5 |
| [String](validations/string.md) | `String` | 17 |
| [Boolean](validations/boolean.md) | `Boolean` | 2 |
| [Integer](validations/integer.md) | `Integer` | 9 |
| [Long](validations/long.md) | `Long` | 6 |
| [Double](validations/double.md) | `Double` | 6 |
| [BigDecimal](validations/bigdecimal.md) | `BigDecimal` | 6 |
| [LocalDate](validations/local-date.md) | `LocalDate` | 6 |
| [LocalTime](validations/local-time.md) | `LocalTime` | 3 |
| [LocalDateTime](validations/local-date-time.md) | `LocalDateTime` | 6 |
| [OffsetDateTime](validations/offset-date-time.md) | `OffsetDateTime` | 6 |
| [Comparable](validations/comparable.md) | `<T extends Comparable<T>>` | 4 |
| [Collection](validations/collection.md) | `Collection<?>` | 6 |
| [Map](validations/map.md) | `Map<?, ?>` | 3 |
| [Array](validations/array.md) | `T[]` | 3 |
| [Brazil](validations/brazil.md) | `String` | 4 |

**Total: 92 validation methods**

## Conventions

- **Optional fields:** For most validations, `null` (and blank strings where applicable) is valid. Use `isNotNull` / `isNotBlank` for required fields.
- **Boolean:** `isTrue` and `isFalse` require an explicit boolean value; `null` fails.
- **Usage:** Chain rules with `.add(validations.method(...))` inside the validator fluent API.

```java
ValidationFactory validations = new ValidationFactoryImpl();

validator.create(dto)
    .field("email", Dto::email)
    .add(validations.isNotBlank("Email is required."))
    .add(validations.email("Invalid email."))
    .end()
    .validate();
```

See the [project README](../README.md) for installation and integration examples.
