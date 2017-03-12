package com.korogi.core.domain.builder;

import com.korogi.core.domain.User;
import java.time.LocalDateTime;

/**
 * Builder class for building test-<code>User</code> entities.<br />
 * <br />
 * Using this builder allows for setting otherwise immutable fields.
 *
 * @author Daan Peelman
 *
 * @see User
 * @see User.UserBuilder
 */
public class TestUserBuilder extends User.UserBuilder {

    private TestUserBuilder() {
        super();
        setBuilder(this);
    }

    private TestUserBuilder(User user) {
        super(user);
        setBuilder(this);
    }

    public static TestUserBuilder newTestUser() {
        return new TestUserBuilder();
    }

    public static TestUserBuilder newTestUser(User user) {
        return new TestUserBuilder(user);
    }

    @Override
    public TestUserBuilder id(Long id) {
        return (TestUserBuilder) super.id(id);
    }

    @Override
    public TestUserBuilder creationDate(LocalDateTime creationDate) {
        return (TestUserBuilder) super.creationDate(creationDate);
    }

    @Override
    public TestUserBuilder createdBy(String createdBy) {
        return (TestUserBuilder) super.createdBy(createdBy);
    }

    @Override
    public TestUserBuilder modificationDate(LocalDateTime modificationDate) {
        return (TestUserBuilder) super.modificationDate(modificationDate);
    }

    @Override
    public TestUserBuilder modifiedBy(String modifiedBy) {
        return (TestUserBuilder) super.modifiedBy(modifiedBy);
    }

    @Override
    public TestUserBuilder version(Long version) {
        return (TestUserBuilder) super.version(version);
    }
}