package com.korogi.core.domain;

import static lombok.AccessLevel.PROTECTED;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Abstract base class for all classes that represent an entity in the database.
 *
 * @author Daan Peelman
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
@ToString
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -2351633402360209718L;

    protected static final String ENTITY_SEQUENCE_GENERATOR = "ENTITY_SEQUENCE_GENERATOR";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ENTITY_SEQUENCE_GENERATOR)
    @Column(name = "id")
    private Long id;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Size(max = 128)
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @Size(max = 128)
    @Column(name = "modified_by")
    private String modifiedBy;

    @Version
    @Column(name = "version")
    private Long version;

    @PrePersist
    protected void prePersist() {
        this.creationDate = LocalDateTime.now();
        this.createdBy = "AUTO"; // TODO set current user
    }

    @PreUpdate
    protected void preUpdate() {
        this.modificationDate = LocalDateTime.now();
        this.modifiedBy = "AUTO"; // TODO set current user
    }
}