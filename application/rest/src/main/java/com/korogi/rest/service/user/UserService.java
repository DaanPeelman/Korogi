package com.korogi.rest.service.user;

import java.util.Optional;
import com.korogi.core.domain.User;

/**
 * @author Daan Peelman
 */
public interface UserService {
    Optional<User> getUserByProviderId(String providerId);
}
