# BigDecimal Validations

**Type:** `BigDecimal`  
**Interface:** `BigDecimalValidations`

| Method | Description |
|--------|-------------|
| `eq(value, message)` | Equal to |
| `min(value, message)` | Greater than or equal to |
| `max(value, message)` | Less than or equal to |
| `between(min, max, message)` | Within inclusive range |
| `gt(threshold, message)` | Greater than |
| `lt(threshold, message)` | Less than |

> For positive, negative, or zero checks, use `gt(BigDecimal.ZERO, message)`, `lt(BigDecimal.ZERO, message)`, or `eq(BigDecimal.ZERO, message)`.

[← Back to index](../README.md)
