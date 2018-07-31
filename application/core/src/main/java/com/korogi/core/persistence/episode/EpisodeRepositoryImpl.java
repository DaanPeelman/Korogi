package com.korogi.core.persistence.episode;

import com.korogi.core.domain.Episode;
import com.korogi.core.persistence.BaseEntityRepositoryImpl;
import org.springframework.stereotype.Repository;

/**
 * Implementation of EpisodeRepository.
 *
 * @author Daan Peelman
 *
 * @see EpisodeRepository
 * @see Episode
 */
@Repository
public class EpisodeRepositoryImpl extends BaseEntityRepositoryImpl<Episode> implements EpisodeRepository {
    public EpisodeRepositoryImpl() {
        super(Episode.class);
    }
}