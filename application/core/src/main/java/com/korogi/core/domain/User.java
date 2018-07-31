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

    @Builder(builderMethodName = "newUser")
    public User(String email, String username, String password, String activationCode, Boolean activated) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.activationCode = activationCode;
        this.activated = activated;
    }
}