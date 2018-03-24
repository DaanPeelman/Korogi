package com.korogi.rest.services.anime;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.korogi.rest.services.BaseServiceTest;
import org.junit.Test;

public class ConsultAnimeServiceTest extends BaseServiceTest {
    private static final String URL = "/anime";

    @Test
    public void consultAnime() throws Exception {
        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_UTF8));
    }
}