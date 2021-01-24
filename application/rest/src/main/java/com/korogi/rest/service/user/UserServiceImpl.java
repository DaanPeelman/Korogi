package com.korogi.rest.service.user;

import java.util.Optional;
import com.korogi.core.domain.User;
import com.korogi.core.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Daan Peelman
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserByProviderId(String providerId) {
        return userRepository.findByProviderId(providerId);
    }
}
