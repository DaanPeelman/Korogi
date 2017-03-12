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
        String username = "john.doe";
        String password = "p@ssw0rd";

        User user = newUser()
                .username(username)
                .password(password)
                .build();

        assertThat(user).isNotNull();
        assertThat(user.username()).isNotNull().isEqualTo(username);
        assertThat(user.password()).isNotNull().isEqualTo(password);
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
        assertThat(copiedUser.username()).isNotNull().isEqualTo(originalUser.username());
        assertThat(copiedUser.password()).isNotNull().isEqualTo(originalUser.password());
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
        String originalUsername = originalUser.username();
        String originalPassword = originalUser.password();
        Long originalId = originalUser.id();
        LocalDateTime originalCreationDate = originalUser.creationDate();
        String originalCreatedBy = originalUser.createdBy();
        LocalDateTime originalModificationDate = originalUser.modificationDate();
        String originalModifiedBy = originalUser.modifiedBy();
        Long originalVersion = originalUser.version();

        String newUsername = "jane.doe";
        String newPassword = "p@ssw0rd123";

        User updatedUser = newUser(originalUser)
                .username(newUsername)
                .password(newPassword)
                .build();

        assertThat(updatedUser).isNotNull().isNotSameAs(originalUser);
        assertThat(updatedUser.username()).isNotNull().isEqualTo(newUsername);
        assertThat(updatedUser.password()).isNotNull().isEqualTo(newPassword);
        assertThat(updatedUser.id()).isNotNull().isEqualTo(originalUser.id());
        assertThat(updatedUser.creationDate()).isNotNull().isEqualTo(originalUser.creationDate());
        assertThat(updatedUser.createdBy()).isNotNull().isEqualTo(originalUser.createdBy());
        assertThat(updatedUser.modificationDate()).isNotNull().isEqualTo(originalUser.modificationDate());
        assertThat(updatedUser.modifiedBy()).isNotNull().isEqualTo(originalUser.modifiedBy());
        assertThat(updatedUser.version()).isNotNull().isEqualTo(originalUser.version());

        assertThat(originalUser.username()).isEqualTo(originalUsername);
        assertThat(originalUser.password()).isEqualTo(originalPassword);
        assertThat(originalUser.id()).isEqualTo(originalId);
        assertThat(originalUser.creationDate()).isEqualTo(originalCreationDate);
        assertThat(originalUser.createdBy()).isEqualTo(originalCreatedBy);
        assertThat(originalUser.modificationDate()).isEqualTo(originalModificationDate);
        assertThat(originalUser.modifiedBy()).isEqualTo(originalModifiedBy);
        assertThat(originalUser.version()).isEqualTo(originalVersion);
    }
}