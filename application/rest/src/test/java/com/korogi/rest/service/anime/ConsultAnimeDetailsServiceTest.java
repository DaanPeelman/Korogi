package com.korogi.rest.service.anime;

import static com.korogi.dto.AnimeDTO.newAnimeDTO;
import static com.korogi.rest.service.matcher.DTOMatchers.containsResourceLinks;
import static com.korogi.rest.service.matcher.DTOMatchers.matchesAnimeDTO;
import static com.korogi.rest.service.matcher.DTOMatchers.matchesErrorDTO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.korogi.dto.AnimeDTO;
import com.korogi.rest.service.BaseServiceTest;
import org.junit.Test;

public class ConsultAnimeDetailsServiceTest extends BaseServiceTest {
    private static final String URL = "/anime/{id}";

    /**
     * When consulting an existing Anime's details, it should return:
     * OK http status
     * JSON content type
     * a JSON corresponding to the Anime with the given id in the database
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimeDetailsServiceTest_consultAnimeDetails.xml")
    public void consultAnimeDetails() throws Exception {
        AnimeDTO expectedAnimeDTO = newAnimeDTO()
                .nameEnglish("Steins;Gate")
                .nameRomanized("Steins;Gate")
                .startAir(LocalDate.of(2011, 4, 6))
                .endAir(LocalDate.of(2011, 9, 14))
                .synopsis("Steins;Gate synopsis here")
                .backdropUrl("assets/images/backdrop.jpg")
                .posterUrl("assets/poster.jpg")
                .build();

        performAndPrint(get(URL, 1))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", matchesAnimeDTO(expectedAnimeDTO)))
            .andExpect(jsonPath("$.links", containsResourceLinks(EXPECTED_ANIME_DETAILS_LINKS)));

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
                .andExpect(jsonPath("$", matchesErrorDTO(RESOURCE_NOT_FOUND_ERROR_DTO)));

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}