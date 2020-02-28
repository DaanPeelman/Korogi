package com.korogi.api;

import com.korogi.dto.KeycloakConfigDTO;

/**
 * @author Daan Peelman
 */
public interface ConfigRestService {
    KeycloakConfigDTO getClientKeycloakConfig();
}