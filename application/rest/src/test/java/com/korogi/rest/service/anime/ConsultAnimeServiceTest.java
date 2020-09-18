package com.korogi.rest.service.anime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.korogi.rest.BaseServiceTest;
import com.korogi.rest.util.HibernateStatisticsUtil;
import org.junit.jupiter.api.Test;

class ConsultAnimeServiceTest extends BaseServiceTest {
    private static final String URL = "/anime";

    @Test
    void consultAnime() throws Exception {
        performAndPrint(get(URL))
            .andExpect(status().isOk());

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}