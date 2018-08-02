package com.korogi.core.persistence.anime;

import static com.korogi.core.persistence.util.PrefetcherUtil.prefetch;

import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import com.korogi.core.domain.Anime;
import com.korogi.core.domain.Anime_;
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

    @Override
    public List<Anime> findByCriteria() {
        // TODO real criteria implementation
        TypedQuery<Anime> query = em.createQuery("select a from Anime a", Anime.class);

        return query.getResultList();
    }

    @Override
    public Optional<Anime> findPrequalOfAnime(Long id, String... relationsToPrefetch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Anime> query = cb.createQuery(Anime.class);

        Root<Anime> anime = query.from(Anime.class);
        Join<Anime, Anime> prequal = anime.join(Anime_.prequal);
        query.select(prequal);
        query.where(
                cb.equal(anime.get(Anime_.id), id)
        );

        prefetch(prequal, relationsToPrefetch);

        return toOptional(em.createQuery(query));
    }

    @Override
    public Optional<Anime> findSequalOfAnime(Long id, String... relationsToPrefetch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Anime> query = cb.createQuery(Anime.class);

        Root<Anime> anime = query.from(Anime.class);
        Join<Anime, Anime> sequal = anime.join(Anime_.sequal);
        query.select(sequal);
        query.where(
                cb.equal(anime.get(Anime_.id), id)
        );

        prefetch(sequal, relationsToPrefetch);

        return toOptional(em.createQuery(query));
    }
}