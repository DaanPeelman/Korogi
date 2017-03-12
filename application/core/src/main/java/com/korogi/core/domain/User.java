package com.korogi.core.domain;

import static com.korogi.core.domain.BaseEntity.ENTITY_SEQUENCE_GENERATOR;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity class representing a User in the database.
 *
 * @author Daan Peelman
 *
 * @see BaseEntity
 */
@Entity
@Table(name = "USERS")
@SequenceGenerator(name = ENTITY_SEQUENCE_GENERATOR, sequenceName = "SEQ_USER")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(max = 128)
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(max = 128)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(max = 128)
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "activated")
    private Boolean activated;

    @SuppressWarnings(value = "unused")
    protected User() {
        super();
    }

    private User(UserBuilder builder) {
        super(builder);
        this.email = builder.email;
        this.username = builder.username;
        this.password = builder.password;
        this.activated = builder.activated;
    }

    public static UserBuilder newUser() {
        return new UserBuilder();
    }

    public static UserBuilder newUser(User user) {
        return new UserBuilder(user);
    }

    public String email() {
        return this.email;
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

    public Boolean activated() {
        return this.activated;
    }

    /**
     * Builder class for building <code>User</code> entities.
     *
     * @author Daan Peelman
     *
     * @see User
     * @see com.korogi.core.domain.BaseEntity.BaseBuilder
     */
    @SuppressWarnings("unchecked")
    public static class UserBuilder<B extends UserBuilder> extends BaseBuilder<User, UserBuilder> {
        private String email;
        private String username;
        private String password;
        private Boolean activated;

        protected UserBuilder() {
            super();
            setBuilder(this);
        }

        protected UserBuilder(User user) {
            super(user);
            this.email = user.email;
            this.username = user.username;
            this.password = user.password;
            this.activated = user.activated;
            setBuilder(this);
        }

        public B email(String email) {
            this.email = email;
            return (B) builder;
        }

        public B username(String username) {
            this.username = username;
            return (B) builder;
        }

        public B password(String password) {
            this.password = password;
            return (B) builder;
        }

        public B activate() {
            this.activated = true;
            return (B) builder;
        }

        public B deactivate() {
            this.activated = false;
            return (B) builder;
        }

        @Override
        public final User build() {
            return new User(builder);
        }
    }
}