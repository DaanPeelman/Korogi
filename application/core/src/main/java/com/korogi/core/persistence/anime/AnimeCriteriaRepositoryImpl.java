package com.korogi.core.persistence.anime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import com.korogi.core.domain.Anime;
import org.springframework.stereotype.Repository;

/**
 * Implementation of AnimeCriteriaRepository.
 *
 * @author Daan Peelman
 *
 * @see AnimeCriteriaRepository
 * @see Anime
 */
@Repository
class AnimeCriteriaRepositoryImpl implements AnimeCriteriaRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Anime> findByCriteria() {
        // TODO real criteria implementation
        TypedQuery<Anime> query = em.createQuery("select a from Anime a", Anime.class);

        return query.getResultList();
    }
}