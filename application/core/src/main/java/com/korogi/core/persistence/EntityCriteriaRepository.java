package com.korogi.core.persistence;

import java.util.List;
import com.korogi.core.domain.BaseEntity;

/**
 * @author Daan Peelman
 */
public interface EntityCriteriaRepository<E extends BaseEntity> {
    /**
     * Retrieves all entities from the database that correspond to a search criteria.
     *
     * @return a list containing all the entities corresponding to the given search criteria
     */
    List<E> findByCriteria();
}