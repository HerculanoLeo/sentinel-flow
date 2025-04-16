package com.herculanoleo.sentinelflow.mock;

import java.time.LocalDate;

public record UserMock(
        String name,
        String lastName,
        LocalDate birthday
) {

    public static UserMock johnDoe() {
        return new UserMock(
                "John",
                "Doe",
                LocalDate.of(2000, 1, 1)
        );
    }

}
