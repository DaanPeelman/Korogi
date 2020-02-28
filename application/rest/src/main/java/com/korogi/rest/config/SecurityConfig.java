package com.korogi.rest.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.representations.adapters.config.AdapterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Component;

/**
 * @author Daan Peelman
 */
@KeycloakConfiguration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) {
        KeycloakAuthenticationProvider provider = keycloakAuthenticationProvider();
        provider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());

        authenticationManagerBuilder.authenticationProvider(provider);
    }

    @Component
    public static class CustomKeycloakConfigResolver implements KeycloakConfigResolver {

        private final String serverUrl;
        private final String realm;
        private final String clientId;
        private final String sslRequired;
        private final String keystoreLocation;
        private final String keystorePassword;

        private final boolean disableTrustManager;

        @Autowired
        public CustomKeycloakConfigResolver(
                @Value("${keycloak.serverUrl}") String serverUrl,
                @Value("${keycloak.realm}") String realm,
                @Value("${keycloak.backend.clientId}") String clientId,
                @Value("${keycloak.backend.sslRequired}") String sslRequired,
                @Value("${keycloak.backend.keystoreLocation}") String keystoreLocation,
                @Value("${keycloak.backend.keystorePassword}") String keystorePassword,

                @Value("${keycloak.disableTrustManager:false}") boolean disableTrustManager
        ) {
            this.serverUrl = serverUrl;
            this.realm = realm;
            this.clientId = clientId;
            this.sslRequired = sslRequired;
            this.keystoreLocation = keystoreLocation;
            this.keystorePassword = keystorePassword;

            this.disableTrustManager = disableTrustManager;
        }

        @Override
        public KeycloakDeployment resolve(HttpFacade.Request facade) {
            AdapterConfig adapterConfig = new AdapterConfig();
            adapterConfig.setRealm(realm);
            adapterConfig.setAuthServerUrl(serverUrl);
            adapterConfig.setSslRequired(sslRequired);
            adapterConfig.setResource(clientId);
            adapterConfig.setClientKeystore(keystoreLocation);
            adapterConfig.setClientKeystorePassword(keystorePassword);
            adapterConfig.setPublicClient(true);
            adapterConfig.setBearerOnly(true);

            adapterConfig.setDisableTrustManager(disableTrustManager);

            return KeycloakDeploymentBuilder.build(adapterConfig);
        }
    }
}