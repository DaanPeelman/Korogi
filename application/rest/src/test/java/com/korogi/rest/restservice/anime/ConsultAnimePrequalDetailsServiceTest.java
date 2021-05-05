package com.korogi.rest.restservice.anime;

import static com.korogi.rest.util.CustomContentResultMatchers.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.korogi.rest.BaseServiceTest;
import com.korogi.rest.util.HibernateStatisticsUtil;
import org.junit.jupiter.api.Test;

class ConsultAnimePrequalDetailsServiceTest extends BaseServiceTest {
    private static final String URL = "/anime/{id}/prequal";

    /**
     * When consulting an existing an Anime's prequal's details, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the prequal of the Anime with the given id in the database
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/restservice/anime/ConsultAnimePrequalDetailsServiceTest_consultAnimePrequalDetails.xml")
    void consultAnimePrequalDetails() throws Exception {
        performAndPrint(get(URL, 2))
            .andExpect(status().isOk())
            .andExpect(json().matchesFileContent(
                "com/korogi/rest/restservice/anime/ConsultAnimePrequalDetailsServiceTest_consultAnimePrequalDetails_expected.json"));

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting an existing an Anime's prequal's details that has a prequal and a sequal, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the prequal of the Anime with the given id in the database
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/restservice/anime/ConsultAnimePrequalDetailsServiceTest_consultAnimePrequalDetails.xml")
    void consultAnimePrequalDetails_withPrequalAndSequal() throws Exception {
        performAndPrint(get(URL, 3))
            .andExpect(status().isOk())
            .andExpect(json().matchesFileContent(
                "com/korogi/rest/restservice/anime/ConsultAnimePrequalDetailsServiceTest_consultAnimePrequalDetails_withPrequalAndSequal_expected.json"));

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting an existing an Anime that has no prequal its details, it should return:
     * NOT FOUND http status
     * JSON content type
     * a JSON corresponding to an Error with status 'Not Found', code '404' and message saying 'Resource was not found'
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/restservice/anime/ConsultAnimePrequalDetailsServiceTest_consultAnimePrequalDetails.xml")
    void consultAnimePrequalDetails_hasNoPrequal() throws Exception {
        performAndPrint(get(URL, 1))
            .andExpect(status().isNotFound())
            .andExpect(json().matchesFileContent("com/korogi/rest/restservice/notFound_expected.json"));

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting a not existing Anime's details, it should return:
     * NOT FOUND http status
     * JSON content type
     * a JSON corresponding to an Error with status 'Not Found', code '404' and message saying 'Resource was not found'
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/restservice/anime/ConsultAnimePrequalDetailsServiceTest_consultAnimePrequalDetails.xml")
    void consultAnimePrequalDetails_notExisting() throws Exception {
        performAndPrint(get(URL, 99))
            .andExpect(status().isNotFound())
            .andExpect(json().matchesFileContent("com/korogi/rest/restservice/notFound_expected.json"));

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}