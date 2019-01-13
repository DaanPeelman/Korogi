package com.korogi.rest.advice;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.korogi.core.persistence.anime.AnimeRepository;
import com.korogi.rest.config.TestRestControllerExceptionAdviceConfig;
import com.korogi.rest.service.BaseServiceTest;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Daan Peelman
 */
@ContextConfiguration(classes = { TestRestControllerExceptionAdviceConfig.class })
public class RestControllerExceptionAdviceTest extends BaseServiceTest {

    @Autowired
    private AnimeRepository animeRepository;

    @After
    public void afterTest() {
        reset(animeRepository);
    }

    @Test
    public void handleThrowable() throws Exception {
        when(animeRepository.findByCriteria()).thenThrow(new RuntimeException());

        performAndPrint(get("/anime"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status", equalTo("Internal Server Error")))
                .andExpect(jsonPath("$.code", equalTo(500)))
                .andExpect(jsonPath("$.message", Matchers.startsWith("An unexpected error occurred. If this problem keeps persisting please contact our technical support with code: '")));

        // TODO assert line has been logged
    }
}