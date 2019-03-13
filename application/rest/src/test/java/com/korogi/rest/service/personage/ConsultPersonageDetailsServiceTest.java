package com.korogi.rest.service.personage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.korogi.rest.service.BaseServiceTest;
import org.junit.jupiter.api.Test;

class ConsultPersonageDetailsServiceTest extends BaseServiceTest {
    private static final String URL = "/personages/{id}";

    /**
     * When consulting an existing Personage's details, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the Personage with the given id in the database
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/personage/ConsultPersonageDetailsServiceTest_consultPersonageDetails.xml")
    void consultPersonageDetails() throws Exception {
        performAndPrint(get(URL, 1))
                .andExpect(status().isOk())
                .andExpectResponseMatchingFile("com/korogi/rest/service/personage/ConsultPersonageDetailsServiceTest_consultPersonageDetails_expected.json");

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting a not existing Personage's details, it should return:
     * NOT FOUND http status
     * JSON content type
     * a JSON corresponding to an Error with status 'Not Found', code '404' and message saying 'Resource was not found'
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/personage/ConsultPersonageDetailsServiceTest_consultPersonageDetails.xml")
    void consultPersonageDetails_notExisting() throws Exception {
        performAndPrint(get(URL, 99))
                .andExpect(status().isNotFound())
                .andExpectResponseMatchingFile("com/korogi/rest/service/personage/ConsultPersonageDetailsServiceTest_consultPersonageDetails_notExisting_expected.json");

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}