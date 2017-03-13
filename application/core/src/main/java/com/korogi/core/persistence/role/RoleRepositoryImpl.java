package com.korogi.core.persistence.role;

import com.korogi.core.domain.Role;
import com.korogi.core.persistence.BaseEntityRepositoryImpl;
import org.springframework.stereotype.Repository;

/**
 * Implementation of RoleRepository.
 *
 * @author Daan Peelman
 *
 * @see RoleRepository
 * @see Role
 */
@Repository
public class RoleRepositoryImpl extends BaseEntityRepositoryImpl<Role> implements RoleRepository {
    public RoleRepositoryImpl() {
        super(Role.class);
    }
}