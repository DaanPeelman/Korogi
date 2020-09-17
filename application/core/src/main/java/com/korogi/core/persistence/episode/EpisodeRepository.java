package com.korogi.core.persistence.episode;

import com.korogi.core.domain.Episode;
import com.korogi.core.persistence.EntityRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CRUD operations with an <code>Episode</code> entity.
 *
 * @author Daan Peelman
 *
 * @see EntityRepository
 * @see Episode
 */
@Repository
public interface EpisodeRepository extends EntityRepository<Episode> {
}