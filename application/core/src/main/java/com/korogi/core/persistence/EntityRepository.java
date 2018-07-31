package com.korogi.core.persistence;

import java.util.Optional;
import com.korogi.core.domain.BaseEntity;

/**
 * Repository to do CRUD operations on entity objects into the database.
 *
 * @author Daan Peelman
 *
 * @param <E> the entity to do CRUD operations with
 *
 * @see BaseEntity
 */
public interface EntityRepository<E extends BaseEntity> {
    /**
     * Retrieves an entity from the database by its id.
     *
     * @param id the id of the entity to find
     * @param relationsToPrefetch the relations of the entity that should (already) be loaded eagerly
     *
     * @return an optional populated with the entity retrieved from the database or an empty optional if none was found
     */
    Optional<E> findById(Long id, String... relationsToPrefetch);

    /**
     * Saves or updates an entity into the database.
     *
     * @param entity the entity to save or update
     *
     * @return a managed version of the entity
     */
    E saveOrUpdate(E entity);

    /**
     * Deletes an entity in the database.
     *
     * @param entity the entity to delete
     */
    void delete(E entity);
}