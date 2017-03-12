package com.korogi.core.domain;

import static com.korogi.core.domain.User.newUser;
import static com.korogi.core.domain.mother.UserMother.johnDoe_updated;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import java.time.LocalDateTime;

/**
 * @author Daan Peelman
 */
public class UserTest {

    /**
     * Should create a new user with the values passed to the builder.
     *
     * @throws Exception
     */
    @Test
    public void testNewUser_new() throws Exception {
        String email = "john.doe@test.com";
        String username = "john.doe";
        String password = "p@ssw0rd";

        User user = newUser()
                .email(email)
                .username(username)
                .password(password)
                .activate()
                .build();

        assertThat(user).isNotNull();
        assertThat(user.email()).isNotNull().isEqualTo(email);
        assertThat(user.username()).isNotNull().isEqualTo(username);
        assertThat(user.password()).isNotNull().isEqualTo(password);
        assertThat(user.activated()).isNotNull().isTrue();
    }

    /**
     * Should create a new user instance with the exact same field values as the original user.
     *
     * @throws Exception
     */
    @Test
    public void testNewUser_copy() throws Exception {
        User originalUser = johnDoe_updated();

        User copiedUser = newUser(originalUser).build();

        assertThat(copiedUser).isNotNull().isNotSameAs(originalUser);
        assertThat(copiedUser.email()).isNotNull().isEqualTo(originalUser.email());
        assertThat(copiedUser.username()).isNotNull().isEqualTo(originalUser.username());
        assertThat(copiedUser.password()).isNotNull().isEqualTo(originalUser.password());
        assertThat(copiedUser.activated()).isNotNull().isEqualTo(originalUser.activated());
        assertThat(copiedUser.id()).isNotNull().isEqualTo(originalUser.id());
        assertThat(copiedUser.creationDate()).isNotNull().isEqualTo(originalUser.creationDate());
        assertThat(copiedUser.createdBy()).isNotNull().isEqualTo(originalUser.createdBy());
        assertThat(copiedUser.modificationDate()).isNotNull().isEqualTo(originalUser.modificationDate());
        assertThat(copiedUser.modifiedBy()).isNotNull().isEqualTo(originalUser.modifiedBy());
        assertThat(copiedUser.version()).isNotNull().isEqualTo(originalUser.version());
    }

    /**
     * Should create a new user, based on the old one but with the updated values that are passed
     * to the builder.
     *
     * @throws Exception
     */
    @Test
    public void testNewUser_updating() throws Exception {
        User originalUser = johnDoe_updated();
        String originalEmail = originalUser.email();
        String originalUsername = originalUser.username();
        String originalPassword = originalUser.password();
        Boolean originalActivated = originalUser.activated();
        Long originalId = originalUser.id();
        LocalDateTime originalCreationDate = originalUser.creationDate();
        String originalCreatedBy = originalUser.createdBy();
        LocalDateTime originalModificationDate = originalUser.modificationDate();
        String originalModifiedBy = originalUser.modifiedBy();
        Long originalVersion = originalUser.version();

        String newEmail = "jane.doe@test.com";
        String newUsername = "jane.doe";
        String newPassword = "p@ssw0rd123";

        User updatedUser = newUser(originalUser)
                .email(newEmail)
                .username(newUsername)
                .password(newPassword)
                .deactivate()
                .build();

        assertThat(updatedUser).isNotNull().isNotSameAs(originalUser);
        assertThat(updatedUser.email()).isNotNull().isEqualTo(newEmail);
        assertThat(updatedUser.username()).isNotNull().isEqualTo(newUsername);
        assertThat(updatedUser.password()).isNotNull().isEqualTo(newPassword);
        assertThat(updatedUser.activated()).isNotNull().isFalse();
        assertThat(updatedUser.id()).isNotNull().isEqualTo(originalUser.id());
        assertThat(updatedUser.creationDate()).isNotNull().isEqualTo(originalUser.creationDate());
        assertThat(updatedUser.createdBy()).isNotNull().isEqualTo(originalUser.createdBy());
        assertThat(updatedUser.modificationDate()).isNotNull().isEqualTo(originalUser.modificationDate());
        assertThat(updatedUser.modifiedBy()).isNotNull().isEqualTo(originalUser.modifiedBy());
        assertThat(updatedUser.version()).isNotNull().isEqualTo(originalUser.version());

        assertThat(originalUser.email()).isEqualTo(originalEmail);
        assertThat(originalUser.username()).isEqualTo(originalUsername);
        assertThat(originalUser.password()).isEqualTo(originalPassword);
        assertThat(originalUser.activated()).isEqualTo(originalActivated);
        assertThat(originalUser.id()).isEqualTo(originalId);
        assertThat(originalUser.creationDate()).isEqualTo(originalCreationDate);
        assertThat(originalUser.createdBy()).isEqualTo(originalCreatedBy);
        assertThat(originalUser.modificationDate()).isEqualTo(originalModificationDate);
        assertThat(originalUser.modifiedBy()).isEqualTo(originalModifiedBy);
        assertThat(originalUser.version()).isEqualTo(originalVersion);
    }
}