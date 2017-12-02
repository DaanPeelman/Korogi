package com.korogi.core.domain.builder;

import static com.korogi.core.domain.User.newUser;

import com.korogi.core.domain.User;
import com.korogi.core.domain.User.UserBuilder;

/**
 * Builder class for building test-<code>User</code> entities.<br />
 * <br />
 * Using this builder allows for setting otherwise immutable fields.
 *
 * @author Daan Peelman
 *
 * @see User
 * @see UserBuilder
 * @see BaseTestEntityBuilder
 */
public class TestUserBuilder extends BaseTestEntityBuilder<User, UserBuilder, TestUserBuilder> {
    private TestUserBuilder() {
        super(newUser());
        setTestEntityBuilder(this);
    }

    private TestUserBuilder(User user) {
        super(newUser(user));
        setTestEntityBuilder(this);
    }

    public static TestUserBuilder newTestUser() {
        return new TestUserBuilder();
    }

    public static TestUserBuilder newTestUser(User user) {
        return new TestUserBuilder(user);
    }

    public TestUserBuilder email(String email) {
        entityBuilder.email(email);
        return this;
    }

    public TestUserBuilder username(String username) {
        entityBuilder.username(username);
        return this;
    }

    public TestUserBuilder password(String password) {
        entityBuilder.password(password);
        return this;
    }

    public TestUserBuilder activationCode(String activationCode) {
        entityBuilder.activationCode(activationCode);
        return this;
    }

    public TestUserBuilder activated(Boolean activated) {
        entityBuilder.activated(activated);
        return this;
    }

    public TestUserBuilder activate() {
        entityBuilder.activate();
        return this;
    }

    public TestUserBuilder deactivate() {
        entityBuilder.deactivate();
        return this;
    }
}