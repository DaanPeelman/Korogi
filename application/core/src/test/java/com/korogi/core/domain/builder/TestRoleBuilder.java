package com.korogi.core.domain.builder;

import static com.korogi.core.domain.Role.newRole;

import com.korogi.core.domain.Role;
import com.korogi.core.domain.Role.RoleBuilder;
import com.korogi.core.domain.enumeration.RoleType;

/**
 * Builder class for building test-<code>Role</code> entities.<br />
 * <br />
 * Using this builder allows for setting otherwise immutable fields.
 *
 * @author Daan Peelman
 *
 * @see Role
 * @see RoleBuilder
 * @see BaseTestEntityBuilder
 */
public class TestRoleBuilder extends BaseTestEntityBuilder<Role, RoleBuilder, TestRoleBuilder> {
    public static TestRoleBuilder newTestRole() {
        return new TestRoleBuilder();
    }

    public static TestRoleBuilder newTestRole(Role role) {
        return new TestRoleBuilder(role);
    }

    private TestRoleBuilder() {
        super(newRole());
        setTestEntityBuilder(this);
    }

    private TestRoleBuilder(Role role) {
        super(newRole(role));
        setTestEntityBuilder(this);
    }

    public TestRoleBuilder roleType(RoleType roleType) {
        entityBuilder.roleType(roleType);

        return this;
    }
}