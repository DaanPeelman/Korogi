package com.korogi.core.domain;

import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Abstract base class for all classes that represent an entity in the database.
 *
 * @author Daan Peelman
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    protected static final String ENTITY_SEQUENCE_GENERATOR = "ENTITY_SEQUENCE_GENERATOR";

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ENTITY_SEQUENCE_GENERATOR)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @NotBlank
    @Size(max = 128)
    @Column(name = "created_by")
    private String createdBy;

    @NotNull
    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @NotBlank
    @Size(max = 128)
    @Column(name = "modified_by")
    private String modifiedBy;

    @NotNull
    @Version
    @Column(name = "version")
    private Long version;

    protected BaseEntity() {
    }

    protected BaseEntity(BaseBuilder builder) {
        this.id = builder.id;
        this.creationDate = builder.creationDate;
        this.createdBy = builder.createdBy;
        this.modificationDate = builder.modificationDate;
        this.modifiedBy = builder.modifiedBy;
        this.version = builder.version;
    }

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

    public Long id() {
        return this.id;
    }

    public LocalDateTime creationDate() {
        return this.creationDate;
    }

    public String createdBy() {
        return this.createdBy;
    }

    public LocalDateTime modificationDate() {
        return this.modificationDate;
    }

    public String modifiedBy() {
        return this.modifiedBy;
    }

    public Long version() {
        return this.version;
    }

    /**
     * Abstract base class for builders.
     *
     * @author Daan Peelman
     *
     * @param <E> the type of the <code>BaseEntity</code> to build
     * @param <B> the type of the <code>EntityBuilder</code> that is being used to build
     *
     * @see BaseEntity
     */
    public abstract static class BaseBuilder<E extends BaseEntity, B extends BaseBuilder> {
        protected B builder;

        private Long id;
        private LocalDateTime creationDate;
        private String createdBy;
        private LocalDateTime modificationDate;
        private String modifiedBy;
        private Long version;

        protected BaseBuilder() {
        }

        protected BaseBuilder(E entity) {
            this.id = entity.id();
            this.creationDate = entity.creationDate();
            this.createdBy = entity.createdBy();
            this.modificationDate = entity.modificationDate();
            this.modifiedBy = entity.modifiedBy();
            this.version = entity.version();
        }

        protected void setBuilder(B builder) {
            this.builder = builder;
        }

        protected B id(Long id) {
            this.id = id;
            return builder;
        }

        protected B creationDate(LocalDateTime creationDate){
            this.creationDate = creationDate;
            return builder;
        }

        protected B createdBy(String createdBy) {
            this.createdBy = createdBy;
            return builder;
        }

        protected B modificationDate(LocalDateTime modificationDate) {
            this.modificationDate = modificationDate;
            return builder;
        }

        protected B modifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
            return builder;
        }

        protected B version(Long version) {
            this.version = version;
            return builder;
        }

        /**
         * Builds an entity based on the values set in the builder.
         *
         * @return the entity based on the values set in the builder
         */
        public abstract E build();
    }
}