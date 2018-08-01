package com.korogi.rest.service.anime;

import static com.korogi.dto.PersonageDTO.newPersonageDTO;
import static com.korogi.rest.service.matcher.DTOMatchers.containsResourceLinks;
import static com.korogi.rest.service.matcher.DTOMatchers.matchesErrorDTO;
import static com.korogi.rest.service.matcher.DTOMatchers.matchesPersonageDTOs;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.korogi.dto.PersonageDTO;
import com.korogi.rest.service.BaseServiceTest;
import org.junit.Test;

public class ConsultAnimePersonagesServiceTest extends BaseServiceTest {
    private static final String URL = "/anime/{id}/personages";

    /**
     * When consulting an existing Anime's personages, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the Personages of the Anime with the given id in the database
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/personage/ConsultAnimePersonagesServiceTest_consultAnimePersonages.xml")
    public void consultAnimePersonages() throws Exception {
        PersonageDTO[] expectedPersonageDTOs = new PersonageDTO[] {
            newPersonageDTO()
                    .firstName("Okabe")
                    .lastName("Rintarou")
                    .photoUrl("http://photo.url.be/okabe.rintarou")
                    .build(),
            newPersonageDTO()
                    .firstName("Makise")
                    .lastName("Kurisu")
                    .photoUrl("http://photo.url.be/makise.kurisu")
                    .build()
        };

        performAndPrint(get(URL, 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(expectedPersonageDTOs.length)))
                .andExpect(jsonPath("$.content", matchesPersonageDTOs(expectedPersonageDTOs)))
                .andExpect(jsonPath("$.content[0].links", containsResourceLinks(EXPECTED_PERSONAGE_DETAILS_LINKS)))
                .andExpect(jsonPath("$.content[1].links", containsResourceLinks(EXPECTED_PERSONAGE_DETAILS_LINKS)));

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting a not existing Anime's personages, it should return:
     * NOT FOUND http status
     * JSON content type
     * a JSON corresponding to an Error with status 'Not Found', code '404' and message saying 'Resource was not found'
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/personage/ConsultAnimePersonagesServiceTest_consultAnimePersonages.xml")
    public void consultAnimePersonages_notExistingAnime() throws Exception {
        performAndPrint(get(URL, 99))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", matchesErrorDTO(RESOURCE_NOT_FOUND_ERROR_DTO)));

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}