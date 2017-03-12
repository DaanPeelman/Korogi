package com.korogi.core.persistence;

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
     *
     * @return the entity retrieved from the database or null if none was found
     */
    E findById(Long id);

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