package com.korogi.rest.service.anime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.korogi.rest.service.BaseServiceTest;
import org.junit.Test;

public class ConsultAnimeServiceTest extends BaseServiceTest {
    private static final String URL = "/anime";

    @Test
    public void consultAnime() throws Exception {
        performAndPrint(get(URL))
                .andExpect(status().isOk());

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}