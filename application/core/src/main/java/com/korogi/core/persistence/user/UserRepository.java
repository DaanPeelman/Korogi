package com.korogi.core.persistence.user;

import java.util.Optional;
import com.korogi.core.domain.User;
import com.korogi.core.persistence.EntityRepository;

/**
 * Repository for CRUD operations with a <code>User</code> entity.
 *
 * @author Daan Peelman
 * @see EntityRepository
 * @see User
 */
public interface UserRepository extends EntityRepository<User> {
    Optional<User> findByProviderId(String providerId);
}