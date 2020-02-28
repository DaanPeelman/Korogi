package com.korogi.rest.service;

import static com.korogi.dto.KeycloakConfigDTO.newKeycloakConfigDTO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.korogi.api.ConfigRestService;
import com.korogi.dto.KeycloakConfigDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daan Peelman
 */
@RestController
@RequestMapping(value = "config", produces = { APPLICATION_JSON_VALUE })
public class ConfigRestServiceImpl implements ConfigRestService {
    private final KeycloakConfigDTO keycloakConfigDTO;

    public ConfigRestServiceImpl(
            @Value("${keycloak.serverUrl}") String serverUrl,
            @Value("${keycloak.realm}") String realm,
            @Value("${keycloak.frontend.clientId}") String clientId
    ) {
        keycloakConfigDTO = newKeycloakConfigDTO()
                .authServerUrl(serverUrl)
                .realm(realm)
                .resource(clientId)
                .enableCors(true)
                .build();
    }

    @GetMapping(value = "keycloak")
    @Override
    public @ResponseBody KeycloakConfigDTO getClientKeycloakConfig() {
        return keycloakConfigDTO;
    }
}