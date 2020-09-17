package com.korogi.core.persistence.user;

import com.korogi.core.domain.User;
import com.korogi.core.persistence.EntityRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CRUD operations with a <code>User</code> entity.
 *
 * @author Daan Peelman
 *
 * @see EntityRepository
 * @see User
 */
@Repository
public interface UserRepository extends EntityRepository<User> {
}