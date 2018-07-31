package com.korogi.core.persistence;

import static java.util.Collections.singletonMap;

import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.korogi.core.domain.BaseEntity;

/**
 * Abstract base class for classes that do CRUD operations to the database.
 *
 * @param <E> the entity to do CRUD operations with
 *
 * @author Daan Peelman
 *
 * @see BaseEntity
 * @see EntityRepository
 */
public abstract class BaseEntityRepositoryImpl<E extends BaseEntity> implements EntityRepository<E> {
    @PersistenceContext
    protected EntityManager em;

    private Class<E> entityClass;

    public BaseEntityRepositoryImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Optional<E> findById(Long id, String... relationsToPrefetch) {
        EntityGraph<E> graph = em.createEntityGraph(entityClass);
        graph.addAttributeNodes(relationsToPrefetch);

        Map<String, Object> hints = singletonMap("javax.persistence.loadgraph", graph);

        return Optional.ofNullable(em.find(entityClass, id, hints));
    }

    @Override
    public E saveOrUpdate(E entity) {
        if(entity.getId() == null) {
            return save(entity);
        } else {
            return update(entity);
        }
    }

    private E save(E entity) {
        em.persist(entity);
        return entity;
    }

    private E update(E entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(E entity) {
        em.remove(entity);
    }

    protected Optional<E> toOptional(TypedQuery<E> query) {
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}