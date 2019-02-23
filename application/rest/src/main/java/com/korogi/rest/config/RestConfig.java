package com.korogi.rest.config;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import java.util.List;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.korogi.rest.advice.RestControllerExceptionAdvice;
import com.korogi.rest.mapper.ResourceMapper;
import com.korogi.rest.service.AnimeRestServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(
        basePackageClasses = {
                RestControllerExceptionAdvice.class,
                ResourceMapper.class,
                AnimeRestServiceImpl.class
        }
)
public class RestConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .serializationInclusion(NON_NULL)
                .featuresToDisable(
                        FAIL_ON_UNKNOWN_PROPERTIES,
                        WRITE_DATES_AS_TIMESTAMPS,
                        ADJUST_DATES_TO_CONTEXT_TIME_ZONE
                )
                .modules(new JavaTimeModule());

        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }
}