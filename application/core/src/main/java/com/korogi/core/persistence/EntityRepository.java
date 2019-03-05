package com.korogi.core.persistence;

import com.korogi.core.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Repository to do CRUD operations on entity objects into the database.
 *
 * @author Daan Peelman
 *
 * @param <E> the entity to do CRUD operations with
 *
 * @see JpaRepository
 * @see BaseEntity
 */
@NoRepositoryBean
public interface EntityRepository<E extends BaseEntity> extends JpaRepository<E, Long> {
}