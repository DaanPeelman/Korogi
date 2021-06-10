package com.korogi.core.persistence;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.korogi.core.domain.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Repository to do CRUD operations on entity objects into the database.
 *
 * @param <E> the entity to do CRUD operations with
 *
 * @author Daan Peelman
 * @see EntityGraphJpaRepository
 * @see EntityGraphJpaSpecificationExecutor
 * @see BaseEntity
 */
@NoRepositoryBean
public interface EntityRepository<E extends BaseEntity> extends EntityGraphJpaRepository<E, Long>, EntityGraphJpaSpecificationExecutor<E> {
}