package com.korogi.rest.service.anime;

import static com.korogi.dto.EpisodeDTO.newEpisodeDTO;
import static com.korogi.rest.service.matcher.DTOMatchers.containsResourceLinks;
import static com.korogi.rest.service.matcher.DTOMatchers.matchesEpisodeDTOs;
import static com.korogi.rest.service.matcher.DTOMatchers.matchesErrorDTO;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.korogi.dto.EpisodeDTO;
import com.korogi.rest.service.BaseServiceTest;
import org.junit.Test;

public class ConsultAnimeEpisodesServiceTest extends BaseServiceTest {
    private static final String URL = "/anime/{id}/episodes";

    /**
     * When consulting an existing Anime's episodes, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the Episodes of the Anime with the given id in the database
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimeEpisodesServiceTest_consultAnimeEpisodes.xml")
    public void consultAnimeEpisodes() throws Exception {
        EpisodeDTO[] expectedEpisodeDTOs = new EpisodeDTO[] {
                newEpisodeDTO()
                        .name("Prologue of the Beginning and Ending")
                        .synopsis("Prologue of the Beginning and Ending synopsis here")
                        .durationInMinutes(24)
                        .airDate(LocalDate.of(2011, 4, 5))
                        .build(),
                newEpisodeDTO()
                        .name("Paranoia of Time Leaps")
                        .synopsis("Paranoia of Time Leaps synopsis here")
                        .durationInMinutes(24)
                        .airDate(LocalDate.of(2011, 4, 12))
                        .build()
        };

        performAndPrint(get(URL, 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(expectedEpisodeDTOs.length)))
                .andExpect(jsonPath("$.content", matchesEpisodeDTOs(expectedEpisodeDTOs)))
                .andExpect(jsonPath("$.content[0].links", containsResourceLinks(EXPECTED_EPISODE_DETAILS_LINKS)))
                .andExpect(jsonPath("$.content[1].links", containsResourceLinks(EXPECTED_EPISODE_DETAILS_LINKS)));

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting a not existing Anime's episodes, it should return:
     * NOT FOUND http status
     * JSON content type
     * a JSON corresponding to an Error with status 'Not Found', code '404' and message saying 'Resource was not found'
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimeEpisodesServiceTest_consultAnimeEpisodes.xml")
    public void consultAnimeEpisodes_notExistingAnime() throws Exception {
        performAndPrint(get(URL, 99))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", matchesErrorDTO(RESOURCE_NOT_FOUND_ERROR_DTO)));

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}