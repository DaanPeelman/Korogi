package com.korogi.core.persistence.anime;

import java.util.Optional;
import com.korogi.core.domain.Anime;
import com.korogi.core.persistence.EntityRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository for CRUD operations with an <code>Anime</code> entity.
 *
 * @author Daan Peelman
 * @see EntityRepository
 * @see Anime
 */
public interface AnimeRepository extends EntityRepository<Anime> {
    @Query("select a.prequal from Anime a where a.id = ?1")
    Optional<Anime> findPrequalOfAnime(Long id);

    @Query("select a.sequal from Anime a where a.id = ?1")
    Optional<Anime> findSequalOfAnime(Long id);
}