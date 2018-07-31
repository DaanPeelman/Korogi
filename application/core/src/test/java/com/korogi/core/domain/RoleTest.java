package com.korogi.core.domain;

import static com.korogi.core.domain.Role.newRole;
import static org.fest.assertions.Assertions.assertThat;

import com.korogi.core.domain.enumeration.RoleType;
import org.junit.Test;

/**
 * @author Daan Peelman
 */
public class RoleTest {

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
}