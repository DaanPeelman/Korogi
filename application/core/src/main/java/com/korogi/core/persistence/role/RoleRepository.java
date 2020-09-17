package com.korogi.core.persistence.role;

import com.korogi.core.domain.Role;
import com.korogi.core.persistence.EntityRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CRUD operations with a <code>Role</code> entity.
 *
 * @author Daan Peelman
 *
 * @see EntityRepository
 * @see Role
 */
@Repository
public interface RoleRepository extends EntityRepository<Role> {
}