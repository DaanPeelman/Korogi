package com.korogi.core.domain.mother;

import static com.korogi.core.domain.builder.TestUserBuilder.newTestUser;

import java.time.LocalDateTime;
import java.time.Month;
import com.korogi.core.domain.User;

/**
 * @author Daan Peelman
 */
public class UserMother {
    public static User johnDoe() {
        return newTestUser()
                .email("john.doe@test.com")
                .username("john.doe")
                .password("p@ssw0rd")
                .activationCode("123456789012345678901234567890123456")
                .deactivate()
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

    public static User johnDoe_updated_notActivated(){
        return newTestUser(johnDoe_updated())
                .activationCode("123456789012345678901234567890123456")
                .deactivate()
                .build();
    }
}