package com.korogi.core.persistence.anime;

import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    public Optional<Anime> findPrequalOfAnime(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Anime> query = cb.createQuery(Anime.class);

        Root<Anime> anime = query.from(Anime.class);
        query.select(anime.get(Anime_.prequal));
        query.where(
                cb.equal(anime.get(Anime_.id), id)
        );

        return toOptional(em.createQuery(query));
    }

    @Override
    public Optional<Anime> findSequalOfAnime(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Anime> query = cb.createQuery(Anime.class);

        Root<Anime> anime = query.from(Anime.class);
        query.select(anime.get(Anime_.sequal));
        query.where(
                cb.equal(anime.get(Anime_.id), id)
        );

        return toOptional(em.createQuery(query));
    }
}