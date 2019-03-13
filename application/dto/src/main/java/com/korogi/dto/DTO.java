package com.korogi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.korogi.dto.annotation.ExcludeFromTypescriptGenerator;

public interface DTO {
    @JsonProperty(value = "type")
    @ExcludeFromTypescriptGenerator
    String type();
}