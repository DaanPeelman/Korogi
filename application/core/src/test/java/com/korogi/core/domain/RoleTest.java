package com.korogi.core.domain;

import static com.korogi.core.domain.Role.newRole;
import static com.korogi.core.domain.mother.RoleMother.admin_updated;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import com.korogi.core.domain.enumeration.RoleType;
import com.korogi.core.validation.ValidationException;
import org.junit.Test;

/**
 * @author Daan Peelman
 */
public class RoleTest {
    /**
     * Should validate when using builder.
     */
    @Test
    public void validate() {
        try {
            newRole().build();
            fail("expected ValidationException to have been thrown");
        } catch (ValidationException e) {
            assertThat(e.getConstraintViolations())
                    .isNotNull()
                    .hasSize(1);
            assertThat(e.getMessage())
                    .isNotNull()
                    .contains("roleType");
        }
    }

    /**
     * Should create a new role with the values passed to the builder.
     */
    @Test
    public void newRole_new() throws Exception {
        RoleType roleType = RoleType.ADMIN;

        Role role = newRole()
                .roleType(roleType)
                .build();

        assertThat(role).isNotNull();
        assertThat(role.getRoleType()).isEqualTo(roleType);
    }

    /**
     * Should create a new role instance with the exact same field values as the original role.
     */
    @Test
    public void newRole_copy() throws Exception {
        Role originalRole = admin_updated();

        Role copiedRole = newRole(originalRole).build();

        assertThat(copiedRole).isNotNull().isNotSameAs(originalRole);
        assertThat(copiedRole.getRoleType()).isNotNull().isEqualTo(originalRole.getRoleType());
        assertThat(copiedRole.getId()).isNotNull().isEqualTo(originalRole.getId());
        assertThat(copiedRole.getCreationDate()).isNotNull().isEqualTo(originalRole.getCreationDate());
        assertThat(copiedRole.getCreatedBy()).isNotNull().isEqualTo(originalRole.getCreatedBy());
        assertThat(copiedRole.getModificationDate()).isNotNull().isEqualTo(originalRole.getModificationDate());
        assertThat(copiedRole.getModifiedBy()).isNotNull().isEqualTo(originalRole.getModifiedBy());
        assertThat(copiedRole.getVersion()).isNotNull().isEqualTo(originalRole.getVersion());
    }

    /**
     * Should create a new role, based on the old one but with the updated values that are passed
     * to the builder.
     */
    @Test
    public void newRole_update() throws Exception {
        Role originalRole = admin_updated();

        RoleType originalRoleType = originalRole.getRoleType();
        Long originalId = originalRole.getId();
        LocalDateTime originalCreationDate = originalRole.getCreationDate();
        String originalCreatedBy = originalRole.getCreatedBy();
        LocalDateTime originalModificationDate = originalRole.getModificationDate();
        String originalModifiedBy = originalRole.getModifiedBy();
        Long originalVersion = originalRole.getVersion();

        RoleType newRoleType = RoleType.USER;

        Role updatedRole = newRole(originalRole)
                .roleType(newRoleType)
                .build();

        assertThat(updatedRole).isNotNull().isNotSameAs(originalRole);
        assertThat(updatedRole.getRoleType()).isNotNull().isEqualTo(newRoleType);
        assertThat(updatedRole.getId()).isNotNull().isEqualTo(originalRole.getId()).isEqualTo(originalId);
        assertThat(updatedRole.getCreationDate()).isNotNull().isEqualTo(originalRole.getCreationDate()).isEqualTo(originalCreationDate);
        assertThat(updatedRole.getCreatedBy()).isNotNull().isEqualTo(originalRole.getCreatedBy()).isEqualTo(originalCreatedBy);
        assertThat(updatedRole.getModificationDate()).isNotNull().isEqualTo(originalRole.getModificationDate()).isEqualTo(originalModificationDate);
        assertThat(updatedRole.getModifiedBy()).isNotNull().isEqualTo(originalRole.getModifiedBy()).isEqualTo(originalModifiedBy);
        assertThat(updatedRole.getVersion()).isNotNull().isEqualTo(originalRole.getVersion()).isEqualTo(originalVersion);

        assertThat(originalRole.getRoleType()).isEqualTo(originalRoleType);
    }
}