package com.korogi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.korogi.dto.annotation.ExcludeFromTypescriptGenerator;

public abstract class BaseDTO {
    @JsonProperty(value = "type")
    @ExcludeFromTypescriptGenerator
    public abstract String type();
}