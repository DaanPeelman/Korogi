package com.korogi.core.domain;

import static com.korogi.core.domain.User.newUser;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;

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
        String activationCode = UUID.randomUUID().toString();

        User user = newUser()
            .email(email)
            .username(username)
            .activationCode(activationCode)
            .activated(true)
            .build();

        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getActivationCode()).isEqualTo(activationCode);
        assertThat(user.getActivated()).isEqualTo(TRUE);
    }
}