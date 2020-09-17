package com.korogi.rest.service.anime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.korogi.rest.BaseServiceTest;
import com.korogi.rest.util.HibernateStatisticsUtil;
import org.junit.jupiter.api.Test;

class ConsultAnimeSequalDetailsServiceTest extends BaseServiceTest {
    private static final String URL = "/anime/{id}/sequal";

    /**
     * When consulting an existing an Anime's sequal's details, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the sequal of the Anime with the given id in the database
     * all default HATEOAS links plus the prequal link
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimeSequalDetailsServiceTest_consultAnimeSequalDetails.xml")
    void consultAnimeSequalDetails() throws Exception {
        performAndPrint(get(URL, 2))
                .andExpect(status().isOk())
                .andExpectResponseMatchingFile("com/korogi/rest/service/anime/ConsultAnimeSequalDetailsServiceTest_consultAnimeSequalDetails_expected.json");

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting an existing an Anime's sequal's details that has a prequal and sequal, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the sequal of the Anime with the given id in the database
     * all default HATEOAS links plus the prequal and sequal link
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimeSequalDetailsServiceTest_consultAnimeSequalDetails.xml")
    void consultAnimeSequalDetails_withPrequalAndSequal() throws Exception {
        performAndPrint(get(URL, 1))
                .andExpect(status().isOk())
                .andExpectResponseMatchingFile("com/korogi/rest/service/anime/ConsultAnimeSequalDetailsServiceTest_consultAnimeSequalDetails_withPrequalAndSequal_expected.json");

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting an existing an Anime that has no sequal its details, it should return:
     * NOT FOUND http status
     * JSON content type
     * a JSON corresponding to an Error with status 'Not Found', code '404' and message saying 'Resource was not found'
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimeSequalDetailsServiceTest_consultAnimeSequalDetails.xml")
    void consultAnimeSequalDetails_hasNoSequal() throws Exception {
        performAndPrint(get(URL, 3))
                .andExpect(status().isNotFound())
                .andExpectResponseMatchingFile("com/korogi/rest/service/anime/ConsultAnimeSequalDetailsServiceTest_consultAnimeSequalDetails_hasNoSequal_expected.json");

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting a not existing Anime's details, it should return:
     * NOT FOUND http status
     * JSON content type
     * a JSON corresponding to an Error with status 'Not Found', code '404' and message saying 'Resource was not found'
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimePrequalDetailsServiceTest_consultAnimePrequalDetails.xml")
    void consultAnimeSequalDetails_notExisting() throws Exception {
        performAndPrint(get(URL, 99))
                .andExpect(status().isNotFound())
                .andExpectResponseMatchingFile("com/korogi/rest/service/anime/ConsultAnimeSequalDetailsServiceTest_consultAnimeSequalDetails_notExisting_expected.json");

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}