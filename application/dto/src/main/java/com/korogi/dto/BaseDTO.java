package com.korogi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BaseDTO {
    @JsonProperty(value = "type")
    public abstract String type();
}