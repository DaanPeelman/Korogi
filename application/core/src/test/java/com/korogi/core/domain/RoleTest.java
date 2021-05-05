package com.korogi.core.domain;

import static com.korogi.core.domain.Role.newRole;
import static org.assertj.core.api.Assertions.assertThat;

import com.korogi.core.domain.enumeration.RoleType;
import org.junit.jupiter.api.Test;

/**
 * @author Daan Peelman
 */
class RoleTest {

    /**
     * Should create a new role with the values passed to the builder.
     */
    @Test
    void newRole_new() throws Exception {
        RoleType roleType = RoleType.ADMIN;

        Role role = newRole()
            .roleType(roleType)
            .build();

        assertThat(role).isNotNull();
        assertThat(role.getRoleType()).isEqualTo(roleType);
    }
}