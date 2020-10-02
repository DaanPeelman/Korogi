package com.korogi.core.domain;

import static com.korogi.core.domain.Assertions.assertThat;
import static com.korogi.core.domain.User.newUser;
import static java.lang.Boolean.TRUE;

import java.util.UUID;
import org.junit.jupiter.api.Test;

/**
 * @author Daan Peelman
 */
class UserTest {

    /**
     * Should create a new user with the values passed to the builder.
     */
    @Test
    void newUser_new() throws Exception {
        String email = "john.doe@test.com";
        String username = "john.doe";
        String password = "p@ssw0rd";
        String activationCode = UUID.randomUUID().toString();

        User user = newUser()
                .email(email)
                .username(username)
                .password(password)
                .activationCode(activationCode)
                .activated(true)
                .build();

        assertThat(user)
                .isNotNull()
                .hasEmail(email)
                .hasUsername(username)
                .hasPassword(password)
                .hasActivationCode(activationCode)
                .hasActivated(TRUE);
    }
}