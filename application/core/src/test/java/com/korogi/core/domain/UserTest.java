package com.korogi.core.domain;

import static com.korogi.core.domain.User.newUser;
import static com.korogi.core.domain.mother.UserMother.johnDoe_updated_notActivated;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Daan Peelman
 */
public class UserTest {

    /**
     * Should create a new user with the values passed to the builder.
     */
    @Test
    public void testNewUser_new() throws Exception {
        String email = "john.doe@test.com";
        String username = "john.doe";
        String password = "p@ssw0rd";
        String activationCode = UUID.randomUUID().toString();

        User user = newUser()
                .email(email)
                .username(username)
                .password(password)
                .activationCode(activationCode)
                .activate()
                .build();

        assertThat(user).isNotNull();
        assertThat(user.email()).isNotNull().isEqualTo(email);
        assertThat(user.username()).isNotNull().isEqualTo(username);
        assertThat(user.password()).isNotNull().isEqualTo(password);
        assertThat(user.activationCode()).isNotNull().isEqualTo(activationCode);
        assertThat(user.activated()).isNotNull().isTrue();
    }

    /**
     * Should create a new user instance with the exact same field values as the original user.
     */
    @Test
    public void testNewUser_copy() throws Exception {
        User originalUser = johnDoe_updated_notActivated();

        User copiedUser = newUser(originalUser).build();

        assertThat(copiedUser).isNotNull().isNotSameAs(originalUser);
        assertThat(copiedUser.email()).isNotNull().isEqualTo(originalUser.email());
        assertThat(copiedUser.username()).isNotNull().isEqualTo(originalUser.username());
        assertThat(copiedUser.password()).isNotNull().isEqualTo(originalUser.password());
        assertThat(copiedUser.activationCode()).isNotNull().isEqualTo(originalUser.activationCode());
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
     */
    @Test
    public void testNewUser_updating() throws Exception {
        User originalUser = johnDoe_updated_notActivated();
        String originalEmail = originalUser.email();
        String originalUsername = originalUser.username();
        String originalPassword = originalUser.password();
        String originalActivationCode = originalUser.activationCode();
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
        String newActivationCode = "456";

        User updatedUser = newUser(originalUser)
                .email(newEmail)
                .username(newUsername)
                .password(newPassword)
                .activationCode(newActivationCode)
                .deactivate()
                .build();

        assertThat(updatedUser).isNotNull().isNotSameAs(originalUser);
        assertThat(updatedUser.email()).isNotNull().isEqualTo(newEmail);
        assertThat(updatedUser.username()).isNotNull().isEqualTo(newUsername);
        assertThat(updatedUser.password()).isNotNull().isEqualTo(newPassword);
        assertThat(updatedUser.activationCode()).isNotNull().isEqualTo(newActivationCode);
        assertThat(updatedUser.activated()).isNotNull().isFalse();
        assertThat(updatedUser.id()).isNotNull().isEqualTo(originalUser.id()).isEqualTo(originalId);
        assertThat(updatedUser.creationDate()).isNotNull().isEqualTo(originalUser.creationDate()).isEqualTo(originalCreationDate);
        assertThat(updatedUser.createdBy()).isNotNull().isEqualTo(originalUser.createdBy()).isEqualTo(originalCreatedBy);
        assertThat(updatedUser.modificationDate()).isNotNull().isEqualTo(originalUser.modificationDate()).isEqualTo(originalModificationDate);
        assertThat(updatedUser.modifiedBy()).isNotNull().isEqualTo(originalUser.modifiedBy()).isEqualTo(originalModifiedBy);
        assertThat(updatedUser.version()).isNotNull().isEqualTo(originalUser.version()).isEqualTo(originalVersion);

        assertThat(originalUser.email()).isEqualTo(originalEmail);
        assertThat(originalUser.username()).isEqualTo(originalUsername);
        assertThat(originalUser.password()).isEqualTo(originalPassword);
        assertThat(originalUser.activationCode()).isEqualTo(originalActivationCode);
        assertThat(originalUser.activated()).isEqualTo(originalActivated);
    }
}