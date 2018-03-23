package com.korogi.rest.services.mapper;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

@Component
public class JavaTimeCompliantJsonMapper extends ObjectMapper {
    public JavaTimeCompliantJsonMapper() {
        super();

        setSerializationInclusion(NON_NULL);
        disable(FAIL_ON_UNKNOWN_PROPERTIES);
        disable(WRITE_DATES_AS_TIMESTAMPS);
        disable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        findAndRegisterModules();
        registerModule(new JavaTimeModule());
    }
}