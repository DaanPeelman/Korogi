package com.korogi.core.domain.mother;

import static com.korogi.core.domain.builder.TestUserBuilder.newTestUser;

import com.korogi.core.domain.User;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * @author Daan Peelman
 */
public class UserMother {
    public static User johnDoe() {
        return newTestUser()
                .username("john.doe")
                .password("p@ssw0rd")
                .build();
    }

    public static User johnDoe_persisted() {
        return newTestUser(johnDoe())
                .id(1L)
                .createdBy("john.doe.created")
                .creationDate(LocalDateTime.of(2017, Month.MARCH, 16, 4, 49, 0))
                .version(1L)
                .build();
    }

    public static User johnDoe_updated() {
        return newTestUser(johnDoe_persisted())
                .modificationDate(LocalDateTime.of(2017, Month.MARCH, 16, 4, 54, 0))
                .modifiedBy("john.doe.updated")
                .build();
    }
}