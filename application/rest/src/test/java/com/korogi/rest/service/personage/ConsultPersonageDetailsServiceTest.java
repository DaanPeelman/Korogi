package com.korogi.rest.service.personage;

import static com.korogi.dto.PersonageDTO.newPersonageDTO;
import static com.korogi.rest.service.matcher.DTOMatchers.containsResourceLinks;
import static com.korogi.rest.service.matcher.DTOMatchers.matchesErrorDTO;
import static com.korogi.rest.service.matcher.DTOMatchers.matchesPersonageDTO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.korogi.dto.PersonageDTO;
import com.korogi.rest.service.BaseServiceTest;
import org.junit.Test;

public class ConsultPersonageDetailsServiceTest extends BaseServiceTest {
    private static final String URL = "/personages/{id}";

    /**
     * When consulting an existing Personage's details, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the Personage with the given id in the database
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/personage/ConsultAnimePersonagesServiceTest_consultAnimePersonages.xml")
    public void consultPersonageDetails() throws Exception {
        PersonageDTO expectedPersonageDTO = newPersonageDTO()
                .firstName("Okabe")
                .lastName("Rintarou")
                .photoUrl("http://photo.url.be/okabe.rintarou")
                .build();

        performAndPrint(get(URL, 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", matchesPersonageDTO(expectedPersonageDTO)))
                .andExpect(jsonPath("$.links", containsResourceLinks(EXPECTED_PERSONAGE_DETAILS_LINKS)));

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting a not existing Personage's details, it should return:
     * NOT FOUND http status
     * JSON content type
     * a JSON corresponding to an Error with status 'Not Found', code '404' and message saying 'Resource was not found'
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/personage/ConsultAnimePersonagesServiceTest_consultAnimePersonages.xml")
    public void consultEpisodeDetails_notExisting() throws Exception {
        performAndPrint(get(URL, 99))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", matchesErrorDTO(RESOURCE_NOT_FOUND_ERROR_DTO)));

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}