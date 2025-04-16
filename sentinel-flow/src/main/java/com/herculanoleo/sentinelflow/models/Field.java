package com.herculanoleo.sentinelflow.models;

public record Field<V>(
        String name,
        V value
) {
}
