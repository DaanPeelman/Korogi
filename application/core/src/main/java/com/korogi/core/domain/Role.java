package com.korogi.core.domain;

import static com.korogi.core.domain.BaseEntity.ENTITY_SEQUENCE_GENERATOR;
import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;
import static lombok.AccessLevel.PUBLIC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.korogi.core.domain.enumeration.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entity class representing a Role in the database.
 *
 * @author Daan Peelman
 * @see BaseEntity
 * @see RoleBuilder
 */
@Data
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PUBLIC)
@Builder(builderMethodName = "newRole")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "ROLES")
@SequenceGenerator(name = ENTITY_SEQUENCE_GENERATOR, sequenceName = "SEQ_ROLE", allocationSize = 1)
public class Role extends BaseEntity {
    private static final long serialVersionUID = 2000395564011152659L;

    @Enumerated(STRING)
    @Column(name = "role_type")
    private RoleType roleType;
}