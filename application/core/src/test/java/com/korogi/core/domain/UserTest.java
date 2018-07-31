package com.korogi.core.domain;

import static com.korogi.core.domain.User.newUser;
import static org.fest.assertions.Assertions.assertThat;

import java.util.UUID;
import org.junit.Test;

/**
 * @author Daan Peelman
 */
public class UserTest {

    /**
     * Should create a new user with the values passed to the builder.
     */
    @Test
    public void newUser_new() throws Exception {
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

        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isNotNull().isEqualTo(email);
        assertThat(user.getUsername()).isNotNull().isEqualTo(username);
        assertThat(user.getPassword()).isNotNull().isEqualTo(password);
        assertThat(user.getActivationCode()).isNotNull().isEqualTo(activationCode);
        assertThat(user.getActivated()).isNotNull().isTrue();
    }
}