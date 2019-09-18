package com.korogi.core.domain;

import static com.korogi.core.domain.BaseEntity.ENTITY_SEQUENCE_GENERATOR;
import static lombok.AccessLevel.PROTECTED;
import static lombok.AccessLevel.PUBLIC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entity class representing a User in the database.
 *
 * @author Daan Peelman
 *
 * @see BaseEntity
 * @see UserBuilder
 */
@Data
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PUBLIC)
@Builder(builderMethodName = "newUser")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "USERS")
@SequenceGenerator(name = ENTITY_SEQUENCE_GENERATOR, sequenceName = "SEQ_USER", allocationSize = 1)
public class User extends BaseEntity {
    private static final long serialVersionUID = -7094382652553816643L;

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
}