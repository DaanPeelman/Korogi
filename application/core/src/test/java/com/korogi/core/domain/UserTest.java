package com.korogi.core.domain;

import static com.korogi.core.domain.User.newUser;
import static com.korogi.core.domain.mother.UserMother.johnDoe_updated_notActivated;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.UUID;
import com.korogi.core.validation.ValidationException;
import org.junit.Test;

/**
 * @author Daan Peelman
 */
public class UserTest {
    /**
     * Should validate when using builder.
     */
    @Test
    public void validate() {
        try {
            newUser().build();
            fail("expected ValidationException to have been thrown");
        } catch (ValidationException e) {
            assertThat(e.getConstraintViolations())
                    .isNotNull()
                    .hasSize(4);
            assertThat(e.getMessage())
                    .isNotNull()
                    .contains("email")
                    .contains("username")
                    .contains("password")
                    .contains("activated");
        }
    }

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
                .activate()
                .build();

        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isNotNull().isEqualTo(email);
        assertThat(user.getUsername()).isNotNull().isEqualTo(username);
        assertThat(user.getPassword()).isNotNull().isEqualTo(password);
        assertThat(user.getActivationCode()).isNotNull().isEqualTo(activationCode);
        assertThat(user.getActivated()).isNotNull().isTrue();
    }

    /**
     * Should create a new user instance with the exact same field values as the original user.
     */
    @Test
    public void newUser_copy() throws Exception {
        User originalUser = johnDoe_updated_notActivated();

        User copiedUser = newUser(originalUser).build();

        assertThat(copiedUser).isNotNull().isNotSameAs(originalUser);
        assertThat(copiedUser.getEmail()).isNotNull().isEqualTo(originalUser.getEmail());
        assertThat(copiedUser.getUsername()).isNotNull().isEqualTo(originalUser.getUsername());
        assertThat(copiedUser.getPassword()).isNotNull().isEqualTo(originalUser.getPassword());
        assertThat(copiedUser.getActivationCode()).isNotNull().isEqualTo(originalUser.getActivationCode());
        assertThat(copiedUser.getActivated()).isNotNull().isEqualTo(originalUser.getActivated());
        assertThat(copiedUser.getId()).isNotNull().isEqualTo(originalUser.getId());
        assertThat(copiedUser.getCreationDate()).isNotNull().isEqualTo(originalUser.getCreationDate());
        assertThat(copiedUser.getCreatedBy()).isNotNull().isEqualTo(originalUser.getCreatedBy());
        assertThat(copiedUser.getModificationDate()).isNotNull().isEqualTo(originalUser.getModificationDate());
        assertThat(copiedUser.getModifiedBy()).isNotNull().isEqualTo(originalUser.getModifiedBy());
        assertThat(copiedUser.getVersion()).isNotNull().isEqualTo(originalUser.getVersion());
    }

    /**
     * Should create a new user, based on the old one but with the updated values that are passed
     * to the builder.
     */
    @Test
    public void newUser_updating() throws Exception {
        User originalUser = johnDoe_updated_notActivated();
        String originalEmail = originalUser.getEmail();
        String originalUsername = originalUser.getUsername();
        String originalPassword = originalUser.getPassword();
        String originalActivationCode = originalUser.getActivationCode();
        Boolean originalActivated = originalUser.getActivated();
        Long originalId = originalUser.getId();
        LocalDateTime originalCreationDate = originalUser.getCreationDate();
        String originalCreatedBy = originalUser.getCreatedBy();
        LocalDateTime originalModificationDate = originalUser.getModificationDate();
        String originalModifiedBy = originalUser.getModifiedBy();
        Long originalVersion = originalUser.getVersion();

        String newEmail = "jane.doe@test.com";
        String newUsername = "jane.doe";
        String newPassword = "p@ssw0rd123";
        String newActivationCode = "123456789012345678901234567890123457";

        User updatedUser = newUser(originalUser)
                .email(newEmail)
                .username(newUsername)
                .password(newPassword)
                .activationCode(newActivationCode)
                .deactivate()
                .build();

        assertThat(updatedUser).isNotNull().isNotSameAs(originalUser);
        assertThat(updatedUser.getEmail()).isNotNull().isEqualTo(newEmail);
        assertThat(updatedUser.getUsername()).isNotNull().isEqualTo(newUsername);
        assertThat(updatedUser.getPassword()).isNotNull().isEqualTo(newPassword);
        assertThat(updatedUser.getActivationCode()).isNotNull().isEqualTo(newActivationCode);
        assertThat(updatedUser.getActivated()).isNotNull().isFalse();
        assertThat(updatedUser.getId()).isNotNull().isEqualTo(originalUser.getId()).isEqualTo(originalId);
        assertThat(updatedUser.getCreationDate()).isNotNull().isEqualTo(originalUser.getCreationDate()).isEqualTo(originalCreationDate);
        assertThat(updatedUser.getCreatedBy()).isNotNull().isEqualTo(originalUser.getCreatedBy()).isEqualTo(originalCreatedBy);
        assertThat(updatedUser.getModificationDate()).isNotNull().isEqualTo(originalUser.getModificationDate()).isEqualTo(originalModificationDate);
        assertThat(updatedUser.getModifiedBy()).isNotNull().isEqualTo(originalUser.getModifiedBy()).isEqualTo(originalModifiedBy);
        assertThat(updatedUser.getVersion()).isNotNull().isEqualTo(originalUser.getVersion()).isEqualTo(originalVersion);

        assertThat(originalUser.getEmail()).isEqualTo(originalEmail);
        assertThat(originalUser.getUsername()).isEqualTo(originalUsername);
        assertThat(originalUser.getPassword()).isEqualTo(originalPassword);
        assertThat(originalUser.getActivationCode()).isEqualTo(originalActivationCode);
        assertThat(originalUser.getActivated()).isEqualTo(originalActivated);
    }
}