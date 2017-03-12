package com.korogi.core.domain;

import static com.korogi.core.domain.BaseEntity.ENTITY_SEQUENCE_GENERATOR;

import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(max = 128)
    @Column(name = "password")
    private String password;

    @SuppressWarnings(value = "unused")
    protected User() {
        super();
    }

    public User(UserBuilder builder) {
        super(builder);
        username = builder.username;
        password = builder.password;
    }

    public static UserBuilder newUser() {
        return new UserBuilder();
    }

    public static UserBuilder newUser(User user) {
        return new UserBuilder(user);
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

    /**
     * Builder class for building <code>User</code> entities.
     *
     * @author Daan Peelman
     *
     * @see User
     * @see com.korogi.core.domain.BaseEntity.BaseBuilder
     */
    public static class UserBuilder extends BaseBuilder<User, UserBuilder> {
        private String username;
        private String password;

        protected UserBuilder() {
            super();
            setBuilder(this);
        }

        protected UserBuilder(User user) {
            super(user);
            this.username = user.username;
            this.password = user.password;
            setBuilder(this);
        }

        public UserBuilder username(String username) {
            this.username = username;
            return builder;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return builder;
        }

        public User build() {
            return new User(this);
        }
    }
}