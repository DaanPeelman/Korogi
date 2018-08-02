package com.korogi.core.persistence.anime;

import java.util.List;
import java.util.Optional;
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
    /**
     * Retrieves all anime from the database that correspond to a search criteria.
     *
     * @return a list containing all the anime corresponding to the given search criteria
     */
    List<Anime> findByCriteria();

    /**
     * Retrieves the anime from the database that is the prequal of another anime.
     *
     * @param id the unique identifier of the anime to find the prequal of
     * @param relationsToPrefetch the relations of the entity that should (already) be loaded eagerly
     *
     * @return an optional populated with the entity that is a direct prequal of the anime that corresponds with the given id or an empty optional if none was found
     */
    Optional<Anime> findPrequalOfAnime(Long id, String... relationsToPrefetch);

    /**
     * Retrieves the anime from the database that is the sequal of another anime.
     *
     * @param id the unique identifier of the anime to find the sequal of
     * @param relationsToPrefetch the relations of the entity that should (already) be loaded eagerly
     *
     * @return an optional populated with the entity that is a direct sequal of the anime that corresponds with the given id or an empty optional if none was found
     */
    Optional<Anime> findSequalOfAnime(Long id, String... relationsToPrefetch);
}