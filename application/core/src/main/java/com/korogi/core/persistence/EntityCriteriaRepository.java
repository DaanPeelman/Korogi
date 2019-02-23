package com.korogi.core.persistence;

import java.util.List;
import com.korogi.core.domain.BaseEntity;

/**
 * Criteria repository to do read operations on entity objects in the database.
 *
 * @author Daan Peelman
 *
 * @param <E> the entity to do read operations with
 *
 * @see BaseEntity
 */
public interface EntityCriteriaRepository<E extends BaseEntity> {
    /**
     * Retrieves all entities from the database that correspond to a search criteria.
     *
     * @return a list containing all the entities corresponding to the given search criteria
     */
    List<E> findByCriteria();
}