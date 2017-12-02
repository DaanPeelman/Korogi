package com.korogi.core.persistence.anime;

import com.korogi.core.domain.Anime;
import com.korogi.core.persistence.BaseEntityRepositoryImpl;
import org.springframework.stereotype.Repository;

/**
 * Implementation of AnimeRepository.
 *
 * @author Daan Peelman
 *
 * @see AnimeRepository
 * @see Anime
 */
@Repository
public class AnimeRepositoryImpl extends BaseEntityRepositoryImpl<Anime> implements AnimeRepository {
    public AnimeRepositoryImpl() {
        super(Anime.class);
    }
}