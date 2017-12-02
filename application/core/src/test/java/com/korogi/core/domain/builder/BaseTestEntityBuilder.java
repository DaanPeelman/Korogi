package com.korogi.core.domain.builder;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import com.korogi.core.domain.BaseEntity;
import com.korogi.core.domain.BaseEntity.BaseEntityBuilder;
import lombok.SneakyThrows;

/**
 * Abstract base class for test-entity-builders.<br />
 * <br />
 * Builders extending this class allow for setting otherwise immutable fields.
 *
 * @author Daan Peelman
 *
 * @param <E> the type of the <code>BaseEntity</code> to build
 * @param <EB> the type of the original <code>BaseEntityBuilder</code> that will be used
 * @param <TB> the type of the <code>BaseTestEntityBuilder</code> that inherits this class
 *
 * @see BaseEntity
 */
public class BaseTestEntityBuilder<E extends BaseEntity, EB extends BaseEntityBuilder<E>, TB extends BaseTestEntityBuilder> {
    protected EB entityBuilder;
    protected TB testEntityBuilder;

    private Long id;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime modificationDate;
    private String modifiedBy;
    private Long version;

    protected BaseTestEntityBuilder(EB entityBuilder) {
        this.entityBuilder = entityBuilder;
    }

    protected void setTestEntityBuilder(TB testEntityBuilder) {
        this.testEntityBuilder = testEntityBuilder;
    }

    public TB id(Long id) {
        this.id = id;
        return testEntityBuilder;
    }

    public TB creationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return testEntityBuilder;
    }

    public TB createdBy(String createdBy) {
        this.createdBy = createdBy;
        return testEntityBuilder;
    }

    public TB modificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
        return testEntityBuilder;
    }

    public TB modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return testEntityBuilder;
    }

    public TB version(Long version) {
        this.version = version;
        return testEntityBuilder;
    }

    /**
     * Builds an entity based on the values set in the builder.
     *
     * @return the entity based on the values set in the builder
     */
    public E build() {
        E entity = entityBuilder.build();

        setFieldUsingReflectionWhenValueDifferentFromNull(entity, "id", id);
        setFieldUsingReflectionWhenValueDifferentFromNull(entity, "creationDate", creationDate);
        setFieldUsingReflectionWhenValueDifferentFromNull(entity, "createdBy", createdBy);
        setFieldUsingReflectionWhenValueDifferentFromNull(entity, "modificationDate", modificationDate);
        setFieldUsingReflectionWhenValueDifferentFromNull(entity, "modifiedBy", modifiedBy);
        setFieldUsingReflectionWhenValueDifferentFromNull(entity, "version", version);

        return entity;
    }

    @SneakyThrows
    private void setFieldUsingReflectionWhenValueDifferentFromNull(E entity, String fieldName, Object value) {
        if (value == null) {
            return;
        }

        Field declaredField = BaseEntity.class.getDeclaredField(fieldName);
        declaredField.setAccessible(true);
        declaredField.set(entity, value);
    }
}