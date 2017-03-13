package com.korogi.core.domain.builder;

import com.korogi.core.domain.Role;
import java.time.LocalDateTime;

/**
 * Builder class for building test-<code>Role</code> entities.<br />
 * <br />
 * Using this builder allows for setting otherwise immutable fields.
 *
 * @author Daan Peelman
 *
 * @see Role
 * @see Role.RoleBuilder
 */
public class TestRoleBuilder extends Role.RoleBuilder<TestRoleBuilder> {

    public TestRoleBuilder() {
        super();
        setBuilder(this);
    }

    public TestRoleBuilder(Role role) {
        super(role);
        setBuilder(this);
    }

    @Override
    public TestRoleBuilder id(Long id) {
        return (TestRoleBuilder) super.id(id);
    }

    @Override
    public TestRoleBuilder creationDate(LocalDateTime creationDate) {
        return (TestRoleBuilder) super.creationDate(creationDate);
    }

    @Override
    public TestRoleBuilder createdBy(String createdBy) {
        return (TestRoleBuilder) super.createdBy(createdBy);
    }

    @Override
    public TestRoleBuilder modificationDate(LocalDateTime modificationDate) {
        return (TestRoleBuilder) super.modificationDate(modificationDate);
    }

    @Override
    public TestRoleBuilder modifiedBy(String modifiedBy) {
        return (TestRoleBuilder) super.modifiedBy(modifiedBy);
    }

    @Override
    public TestRoleBuilder version(Long version) {
        return (TestRoleBuilder) super.version(version);
    }
}