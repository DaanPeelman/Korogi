package com.korogi.rest.advice;

import static com.korogi.core.util.InjectorUtil.injectIntoStaticField;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import com.korogi.core.persistence.anime.AnimeRepository;
import com.korogi.core.util.UUIDGenerator;
import com.korogi.rest.config.TestRestControllerExceptionAdviceConfig;
import com.korogi.rest.exception.ResourceNotFoundException;
import com.korogi.rest.service.BaseServiceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Daan Peelman
 */
@ContextConfiguration(classes = { TestRestControllerExceptionAdviceConfig.class })
public class RestControllerExceptionAdviceTest extends BaseServiceTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private UUIDGenerator uuidGenerator;

    private Logger logger;

    @Before
    public void injectLogger() throws Exception {
        this.logger = mock(Logger.class);
        injectIntoStaticField(RestControllerExceptionAdvice.class, "log", logger);
    }

    @After
    public void resetMocks() {
        reset(animeRepository);
        reset(uuidGenerator);
        reset(logger);
    }

    @Test
    public void handleResourceNotFoundException() throws Exception {
        when(animeRepository.findById(1L)).thenThrow(new ResourceNotFoundException());

        performAndPrint(get("/anime/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", equalTo("Not Found")))
                .andExpect(jsonPath("$.code", equalTo(404)))
                .andExpect(jsonPath("$.message", equalTo("Resource not found")));
    }

    @Test
    public void handleThrowable() throws Exception {
        RuntimeException runtimeExceptionToThrow = new RuntimeException();
        String generatedUUID = UUID.randomUUID().toString();

        when(animeRepository.findByCriteria()).thenThrow(runtimeExceptionToThrow);
        when(uuidGenerator.generateUUIDString()).thenReturn(generatedUUID);
        doNothing().when(logger).error(anyString(), any(RuntimeException.class));

        performAndPrint(get("/anime"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status", equalTo("Internal Server Error")))
                .andExpect(jsonPath("$.code", equalTo(500)))
                .andExpect(jsonPath("$.message", startsWith("An unexpected error occurred. If this problem keeps persisting please contact our technical support with code: '" + generatedUUID + "'.")));

        verify(logger, times(1)).error(eq("Unexpected error: '" + generatedUUID + "'"), eq(runtimeExceptionToThrow));
    }
}