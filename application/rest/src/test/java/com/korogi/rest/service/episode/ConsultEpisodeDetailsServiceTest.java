package com.korogi.rest.service.episode;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.korogi.rest.service.BaseServiceTest;
import org.junit.Test;

public class ConsultEpisodeDetailsServiceTest extends BaseServiceTest {
    private static final String URL = "/episodes/{id}";

    /**
     * When consulting an existing Episode's details, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the Episode with the given id in the database
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/episode/ConsultEpisodeDetailsServiceTest_consultEpisodeDetails.xml")
    public void consultEpisodeDetails() throws Exception {
        performAndPrint(get(URL, 1))
                .andExpect(status().isOk())
                .andExpectResponseMatchingFile("com/korogi/rest/service/episode/ConsultEpisodeDetailsServiceTest_consultEpisodeDetails_expected.json");

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting a not existing Episode's details, it should return:
     * NOT FOUND http status
     * JSON content type
     * a JSON corresponding to an Error with status 'Not Found', code '404' and message saying 'Resource was not found'
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/episode/ConsultEpisodeDetailsServiceTest_consultEpisodeDetails.xml")
    public void consultEpisodeDetails_notExisting() throws Exception {
        performAndPrint(get(URL, 99))
                .andExpect(status().isNotFound())
                .andExpectResponseMatchingFile("com/korogi/rest/service/episode/ConsultEpisodeDetailsServiceTest_consultEpisodeDetails_notExisting_expected.json");

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}