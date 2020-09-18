package com.korogi.core.persistence.personage;

import com.korogi.core.domain.Personage;
import com.korogi.core.persistence.EntityRepository;

/**
 * Repository for CRUD operations with a <code>Personage</code> entity.
 *
 * @author Daan Peelman
 *
 * @see EntityRepository
 * @see Personage
 */
public interface PersonageRepository extends EntityRepository<Personage> {
}