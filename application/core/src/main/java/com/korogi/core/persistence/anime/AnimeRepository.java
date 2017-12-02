package com.korogi.core.persistence.anime;

import com.korogi.core.domain.Anime;
import com.korogi.core.persistence.EntityRepository;

/**
 * Repository for CRUD operations with an <code>Anime</code> entity.
 *
 * @author Daan Peelman
 *
 * @see EntityRepository
 * @see Anime
 */
public interface AnimeRepository extends EntityRepository<Anime> {
}