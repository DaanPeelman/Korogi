package com.korogi.dto;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Daan Peelman
 */
public interface DTORelation {
    @JsonValue
    String getValue();
}