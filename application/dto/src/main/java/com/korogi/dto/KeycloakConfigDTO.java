package com.korogi.dto;

import static lombok.AccessLevel.PUBLIC;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Daan Peelman
 */
@Data
@AllArgsConstructor(access = PUBLIC)
@NoArgsConstructor(access = PUBLIC)
@Builder(builderMethodName = "newKeycloakConfigDTO")
public class KeycloakConfigDTO {
    @JsonProperty(value = "auth-server-url")
    private String authServerUrl;
    private String realm;
    private String resource;
    @JsonProperty(value = "enable-cors")
    private boolean enableCors;
}