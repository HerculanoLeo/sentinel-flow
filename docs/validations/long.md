# Long Validations

**Type:** `Long`  
**Interface:** `LongValidations`

| Method | Description |
|--------|-------------|
| `eq(value, message)` | Equal to |
| `min(value, message)` | Greater than or equal to |
| `max(value, message)` | Less than or equal to |
| `between(min, max, message)` | Within inclusive range |
| `gt(threshold, message)` | Greater than |
| `lt(threshold, message)` | Less than |

> For positive, negative, or zero checks, use `gt(0L, message)`, `lt(0L, message)`, or `eq(0L, message)`.

[← Back to index](../README.md)
