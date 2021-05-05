package com.korogi.core.persistence.episode;

import com.korogi.core.domain.Episode;
import com.korogi.core.persistence.EntityRepository;

/**
 * Repository for CRUD operations with an <code>Episode</code> entity.
 *
 * @author Daan Peelman
 * @see EntityRepository
 * @see Episode
 */
public interface EpisodeRepository extends EntityRepository<Episode> {
}