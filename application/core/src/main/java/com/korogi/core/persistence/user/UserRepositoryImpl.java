package com.korogi.core.persistence.user;

import com.korogi.core.domain.User;
import com.korogi.core.persistence.BaseEntityRepository;
import org.springframework.stereotype.Repository;

/**
 * Implementation of UserRepository.
 *
 * @author Daan Peelman
 *
 * @see UserRepository
 * @see User
 */
@Repository
public class UserRepositoryImpl extends BaseEntityRepository<User> implements UserRepository {
    public UserRepositoryImpl() {
        super(User.class);
    }
}