package com.korogi.rest.restservice.personage;

import static com.korogi.rest.util.CustomContentResultMatchers.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.korogi.rest.BaseServiceTest;
import com.korogi.rest.util.HibernateStatisticsUtil;
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
    @DatabaseSetup("/com/korogi/rest/restservice/personage/ConsultPersonageDetailsServiceTest_consultPersonageDetails.xml")
    void consultPersonageDetails() throws Exception {
        performAndPrint(get(URL, 1))
            .andExpect(status().isOk())
            .andExpect(json().matchesFileContent("com/korogi/rest/restservice/personage/ConsultPersonageDetailsServiceTest_consultPersonageDetails_expected.json"));

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting a not existing Personage's details, it should return:
     * NOT FOUND http status
     * JSON content type
     * a JSON corresponding to an Error with status 'Not Found', code '404' and message saying 'Resource was not found'
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/restservice/personage/ConsultPersonageDetailsServiceTest_consultPersonageDetails.xml")
    void consultPersonageDetails_notExisting() throws Exception {
        performAndPrint(get(URL, 99))
            .andExpect(status().isNotFound())
            .andExpect(json().matchesFileContent("com/korogi/rest/restservice/notFound_expected.json"));

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}