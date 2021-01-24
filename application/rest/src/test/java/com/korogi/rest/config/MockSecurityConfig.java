package com.korogi.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

/**
 * @author Daan Peelman
 */
@Configuration
public class MockSecurityConfig {

    @Bean
    public ClientRegistrationRepository dummyRegistrationRepository() {
        return registrationId -> null;
    }
}
