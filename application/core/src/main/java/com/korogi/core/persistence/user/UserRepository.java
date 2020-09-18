package com.korogi.core.persistence.user;

import com.korogi.core.domain.User;
import com.korogi.core.persistence.EntityRepository;

/**
 * Repository for CRUD operations with a <code>User</code> entity.
 *
 * @author Daan Peelman
 *
 * @see EntityRepository
 * @see User
 */
public interface UserRepository extends EntityRepository<User> {
}