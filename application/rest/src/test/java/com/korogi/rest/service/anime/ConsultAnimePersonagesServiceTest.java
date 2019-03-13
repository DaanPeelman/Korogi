package com.korogi.rest.service.anime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.korogi.rest.service.BaseServiceTest;
import org.junit.jupiter.api.Test;

class ConsultAnimePersonagesServiceTest extends BaseServiceTest {
    private static final String URL = "/anime/{id}/personages";

    /**
     * When consulting an existing Anime's personages, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the Personages of the Anime with the given id in the database
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimePersonagesServiceTest_consultAnimePersonages.xml")
    void consultAnimePersonages() throws Exception {
        performAndPrint(get(URL, 1))
                .andExpect(status().isOk())
                .andExpectResponseMatchingFile("com/korogi/rest/service/anime/ConsultAnimePersonagesServiceTest_consultAnimePersonages_expected.json");

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(2);
    }

    /**
     * When consulting a not existing Anime's personages, it should return:
     * NOT FOUND http status
     * JSON content type
     * a JSON corresponding to an Error with status 'Not Found', code '404' and message saying 'Resource was not found'
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimePersonagesServiceTest_consultAnimePersonages.xml")
    void consultAnimePersonages_notExistingAnime() throws Exception {
        performAndPrint(get(URL, 99))
                .andExpect(status().isNotFound())
                .andExpectResponseMatchingFile("com/korogi/rest/service/anime/ConsultAnimePersonagesServiceTest_consultAnimePersonages_notExistingAnime_expected.json");

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}