# Comparable Validations

**Type:** `<T extends Comparable<T>>`  
**Interface:** `ComparableValidations`

Useful for types such as `OffsetDateTime`, `Instant`, and `ZonedDateTime`.

| Method | Description |
|--------|-------------|
| `comparableEq(value, message)` | Equal to |
| `comparableMin(value, message)` | Greater than or equal to |
| `comparableMax(value, message)` | Less than or equal to |
| `comparableBetween(min, max, message)` | Within inclusive range |

[← Back to index](../README.md)
