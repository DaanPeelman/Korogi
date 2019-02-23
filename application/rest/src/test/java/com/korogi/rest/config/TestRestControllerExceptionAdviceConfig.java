package com.korogi.rest.config;

import static org.mockito.Mockito.mock;

import com.korogi.core.persistence.anime.AnimeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Daan Peelman
 */
@Configuration
public class TestRestControllerExceptionAdviceConfig {
    @Bean
    @Primary
    public AnimeRepository mockAnimeRepository() {
        return mock(AnimeRepository.class);
    }
}