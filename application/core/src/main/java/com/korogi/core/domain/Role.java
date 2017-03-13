package com.korogi.core.domain;

import static com.korogi.core.domain.BaseEntity.ENTITY_SEQUENCE_GENERATOR;

import com.korogi.core.domain.enumeration.RoleType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity class representing a Role in the database.
 *
 * @author Daan Peelman
 *
 * @see BaseEntity
 * @see Role.RoleBuilder
 */
@Entity
@Table(name = "ROLES")
@SequenceGenerator(name = ENTITY_SEQUENCE_GENERATOR, sequenceName = "SEQ_ROLE")
public class Role extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;

    @SuppressWarnings("unused")
    protected Role() {
        super();
    }

    private Role(RoleBuilder builder) {
        super(builder);
        roleType = builder.roleType;
    }

    /**
     * Creates a new RoleBuilder to create Roles.
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

    public RoleType roleType() {
        return this.roleType;
    }

    /**
     * Builder class for building <code>Role</code> entities.
     *
     * @author Daan Peelman
     *
     * @param <B> the type of the <code>RoleBuilder</code> that is being used to build
     *
     * @see Role
     * @see BaseEntity.BaseBuilder
     */
    @SuppressWarnings("unchecked")
    public static class RoleBuilder<B extends RoleBuilder> extends BaseBuilder<Role, RoleBuilder> {
        private RoleType roleType;

        protected RoleBuilder() {
            super();
            setBuilder(this);
        }

        protected RoleBuilder(Role role) {
            super(role);
            setBuilder(this);

            this.roleType = role.roleType;
        }

        public B roleType(RoleType roleType) {
            this.roleType = roleType;
            return (B) builder;
        }

        @Override
        public Role build() {
            return new Role(builder);
        }
    }
}