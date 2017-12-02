package com.korogi.core.domain;

import static com.korogi.core.domain.BaseEntity.ENTITY_SEQUENCE_GENERATOR;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Entity class representing a User in the database.
 *
 * @author Daan Peelman
 *
 * @see BaseEntity
 * @see UserBuilder
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "newUser")
@ToString(callSuper = true)
@Entity
@Table(name = "USERS")
@SequenceGenerator(name = ENTITY_SEQUENCE_GENERATOR, sequenceName = "SEQ_USER")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1941052640738015822L;

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

    @Size(min = 36, max = 36)
    @Column(name = "activation_code")
    private String activationCode;

    @NotNull
    @Column(name = "activated")
    private Boolean activated;

    private User(UserBuilder builder) {
        super(builder);
        this.email = builder.email;
        this.username = builder.username;
        this.password = builder.password;
        this.activationCode = builder.activationCode;
        this.activated = builder.activated;
    }

    /**
     * Creates a new UserBuilder to create a new User.
     *
     * @return a new UserBuilder
     */
    public static UserBuilder newUser() {
        return new UserBuilder();
    }

    /**
     * Creates a new UserBuilder with the fields of a given User.<br />
     * <br />
     * Use this to create a copy or update a User.
     *
     * @param user the User instance to copy the field values from
     *
     * @return a new UserBuilder instantiated with the same field values as the User that got passed
     */
    public static UserBuilder newUser(User user) {
        return new UserBuilder(user);
    }

    /**
     * Builder class for building <code>User</code> entities.
     *
     * @author Daan Peelman
     *
     * @see User
     * @see BaseEntityBuilder
     */
    public static class UserBuilder extends BaseEntityBuilder<User> {
        private UserBuilder() {
            super();
        }

        private UserBuilder(User user) {
            super(user);
            this.email = user.email;
            this.username = user.username;
            this.password = user.password;
            this.activationCode = user.activationCode;
            this.activated = user.activated;
        }

        public UserBuilder activate() {
            this.activated = true;
            return this;
        }

        public UserBuilder deactivate() {
            this.activated = false;
            return this;
        }

        @Override
        public User build() {
            User user = new User(this);
            user.validate();

            return user;
        }
    }
}