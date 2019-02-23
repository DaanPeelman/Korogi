package com.korogi.rest.service.anime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.korogi.rest.service.BaseServiceTest;
import org.junit.Test;

public class ConsultAnimeDetailsServiceTest extends BaseServiceTest {
    private static final String URL = "/anime/{id}";

    /**
     * When consulting an existing Anime's details that has a sequal but no prequal, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the Anime with the given id in the database
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimeDetailsServiceTest_consultAnimeDetails.xml")
    public void consultAnimeDetails_animeWithSequal() throws Exception {
        performAndPrint(get(URL, 1))
            .andExpect(status().isOk())
            .andExpectResponseMatchingFile("com/korogi/rest/service/anime/ConsultAnimeDetailsServiceTest_consultAnimeDetails_animeWithSequal_expected.json");

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting an existing Anime's details that has a prequal but no sequal, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the Anime with the given id in the database
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimeDetailsServiceTest_consultAnimeDetails.xml")
    public void consultAnimeDetails_animeWithPrequal() throws Exception {
        performAndPrint(get(URL, 3))
                .andExpect(status().isOk())
                .andExpectResponseMatchingFile("com/korogi/rest/service/anime/ConsultAnimeDetailsServiceTest_consultAnimeDetails_animeWithPrequal_expected.json");

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting an existing Anime's details that has a prequal and a sequal, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the Anime with the given id in the database
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimeDetailsServiceTest_consultAnimeDetails.xml")
    public void consultAnimeDetails_animeWithPrequalAndSequal() throws Exception {
        performAndPrint(get(URL, 2))
                .andExpect(status().isOk())
                .andExpectResponseMatchingFile("com/korogi/rest/service/anime/ConsultAnimeDetailsServiceTest_consultAnimeDetails_animeWithPrequalAndSequal_expected.json");

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting an existing Anime's details that has neither a prequal nor a sequal, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the Anime with the given id in the databases
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimeDetailsServiceTest_consultAnimeDetails.xml")
    public void consultAnimeDetails_animeWithoutPrequalOrSequal() throws Exception {
        performAndPrint(get(URL, 4))
                .andExpect(status().isOk())
                .andExpectResponseMatchingFile("com/korogi/rest/service/anime/ConsultAnimeDetailsServiceTest_consultAnimeDetails_animeWithoutPrequalOrSequal_expected.json");

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting a not existing Anime's details, it should return:
     * NOT FOUND http status
     * JSON content type
     * a JSON corresponding to an Error with status 'Not Found', code '404' and message saying 'Resource was not found'
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimeDetailsServiceTest_consultAnimeDetails.xml")
    public void consultAnimeDetails_notExisting() throws Exception {
        performAndPrint(get(URL, 99))
                .andExpect(status().isNotFound())
                .andExpectResponseMatchingFile("com/korogi/rest/service/anime/ConsultAnimeDetailsServiceTest_consultAnimeDetails_notExisting_expected.json");

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}