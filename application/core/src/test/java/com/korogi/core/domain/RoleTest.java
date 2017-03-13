package com.korogi.core.domain;

import static com.korogi.core.domain.Role.newRole;
import static com.korogi.core.domain.mother.RoleMother.admin_updated;
import static org.fest.assertions.Assertions.assertThat;

import com.korogi.core.domain.enumeration.RoleType;
import org.junit.Test;
import java.time.LocalDateTime;

/**
 * @author Daan Peelman
 */
public class RoleTest {
    @Test
    public void testNewRole_new() throws Exception {
        RoleType roleType = RoleType.ADMIN;

        Role role = newRole()
                .roleType(roleType)
                .build();

        assertThat(role).isNotNull();
        assertThat(role.roleType()).isEqualTo(roleType);
    }

    @Test
    public void testNewRole_copy() throws Exception {
        Role originalRole = admin_updated();

        Role copiedRole = newRole(originalRole).build();

        assertThat(copiedRole).isNotNull();
        assertThat(copiedRole.roleType()).isNotNull().isEqualTo(originalRole.roleType());
        assertThat(copiedRole.id()).isNotNull().isEqualTo(originalRole.id());
        assertThat(copiedRole.creationDate()).isNotNull().isEqualTo(originalRole.creationDate());
        assertThat(copiedRole.createdBy()).isNotNull().isEqualTo(originalRole.createdBy());
        assertThat(copiedRole.modificationDate()).isNotNull().isEqualTo(originalRole.modificationDate());
        assertThat(copiedRole.modifiedBy()).isNotNull().isEqualTo(originalRole.modifiedBy());
        assertThat(copiedRole.version()).isNotNull().isEqualTo(originalRole.version());
    }

    @Test
    public void testNewRole_update() throws Exception {
        Role originalRole = admin_updated();

        RoleType originalRoleType = originalRole.roleType();
        Long originalId = originalRole.id();
        LocalDateTime originalCreationDate = originalRole.creationDate();
        String originalCreatedBy = originalRole.createdBy();
        LocalDateTime originalModificationDate = originalRole.modificationDate();
        String originalModifiedBy = originalRole.modifiedBy();
        Long originalVersion = originalRole.version();

        RoleType newRoleType = RoleType.USER;

        Role updatedRole = newRole(originalRole)
                .roleType(newRoleType)
                .build();

        assertThat(updatedRole).isNotNull();
        assertThat(updatedRole.roleType()).isNotNull().isEqualTo(newRoleType);
        assertThat(updatedRole.id()).isNotNull().isEqualTo(originalRole.id()).isEqualTo(originalId);
        assertThat(updatedRole.creationDate()).isNotNull().isEqualTo(originalRole.creationDate()).isEqualTo(originalCreationDate);
        assertThat(updatedRole.createdBy()).isNotNull().isEqualTo(originalRole.createdBy()).isEqualTo(originalCreatedBy);
        assertThat(updatedRole.modificationDate()).isNotNull().isEqualTo(originalRole.modificationDate()).isEqualTo(originalModificationDate);
        assertThat(updatedRole.modifiedBy()).isNotNull().isEqualTo(originalRole.modifiedBy()).isEqualTo(originalModifiedBy);
        assertThat(updatedRole.version()).isNotNull().isEqualTo(originalRole.version()).isEqualTo(originalVersion);

        assertThat(originalRole.roleType()).isEqualTo(originalRoleType);
    }
}