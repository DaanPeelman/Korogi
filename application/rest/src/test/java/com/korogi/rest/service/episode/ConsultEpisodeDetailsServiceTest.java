package com.korogi.rest.service.episode;

import static com.korogi.dto.EpisodeDTO.newEpisodeDTO;
import static com.korogi.rest.service.matcher.DTOMatchers.containsResourceLinks;
import static com.korogi.rest.service.matcher.DTOMatchers.matchesEpisodeDTO;
import static com.korogi.rest.service.matcher.DTOMatchers.matchesErrorDTO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.korogi.dto.EpisodeDTO;
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
        EpisodeDTO expectedEpisodeDTO = newEpisodeDTO()
                .name("Prologue of the Beginning and Ending")
                .synopsis("Prologue of the Beginning and Ending synopsis here")
                .durationInMinutes(24)
                .airDate(LocalDate.of(2011, 4, 5))
                .build();

        performAndPrint(get(URL, 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", matchesEpisodeDTO(expectedEpisodeDTO)))
                .andExpect(jsonPath("$.links", containsResourceLinks(EXPECTED_EPISODE_DETAILS_LINKS)));

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
                .andExpect(jsonPath("$", matchesErrorDTO(RESOURCE_NOT_FOUND_ERROR_DTO)));

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}