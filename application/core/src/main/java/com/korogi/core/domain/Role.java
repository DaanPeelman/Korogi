package com.korogi.core.domain;

import static com.korogi.core.domain.BaseEntity.ENTITY_SEQUENCE_GENERATOR;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.korogi.core.domain.enumeration.RoleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entity class representing a Role in the database.
 *
 * @author Daan Peelman
 *
 * @see BaseEntity
 * @see RoleBuilder
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "newRole")
@ToString(callSuper = true)
@Entity
@Table(name = "ROLES")
@SequenceGenerator(name = ENTITY_SEQUENCE_GENERATOR, sequenceName = "SEQ_ROLE")
public class Role extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;

    private Role(RoleBuilder builder) {
        super(builder);
        roleType = builder.roleType;
    }

    /**
     * Creates a new RoleBuilder to create a new Role.
     *
     * @return a new RoleBuilder
     */
    public static RoleBuilder newRole() {
        return new RoleBuilder();
    }

    /**
     * Creates a new RoleBuilder with the fields of a given Role.<br />
     * <br />
     * Use this to create a copy or update a Role.
     *
     * @param role the Role instance to copy the field values from
     *
     * @return a new RoleBuilder instantiated with the same field values as the Role that got passed
     */
    public static RoleBuilder newRole(Role role) {
        return new RoleBuilder(role);
    }

    /**
     * Builder class for building <code>Role</code> entities.
     *
     * @author Daan Peelman
     *
     * @see Role
     * @see BaseEntityBuilder
     */
    public static class RoleBuilder extends BaseEntityBuilder<Role> {
        private RoleBuilder() {
            super();
        }

        private RoleBuilder(Role role) {
            super(role);

            this.roleType = role.roleType;
        }

        @Override
        public Role build() {
            Role role = new Role(this);
            role.validate();

            return role;
        }
    }
}