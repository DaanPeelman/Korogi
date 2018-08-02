package com.korogi.rest.service.anime;

import static com.korogi.core.util.ArrayUtil.concatenate;
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

public class ConsultAnimeSequalDetailsServiceTest extends BaseServiceTest {
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
    public void consultAnimeSequalDetails() throws Exception {
        AnimeDTO expectedAnimeDTO = newAnimeDTO()
                .nameEnglish("Steins;Gate: The Movie − Load Region of Déjà Vu")
                .nameRomanized("Steins;Gate Movie: Fuka Ryouiki no Déjà vu")
                .startAir(LocalDate.of(2013, 4, 20))
                .endAir(LocalDate.of(2013, 4, 20))
                .synopsis("Steins;Gate: The Movie − Load Region of Déjà Vu synopsis here")
                .backdropUrl("http://backdrop.url.be/steins.gate.load.region.of.deja.vu")
                .posterUrl("http://poster.url.be/steins.gate.load.region.of.deja.vu")
                .build();

        performAndPrint(get(URL, 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", matchesAnimeDTO(expectedAnimeDTO)))
                .andExpect(jsonPath("$.links", containsResourceLinks(concatenate(EXPECTED_ANIME_DETAILS_LINKS, "prequal"))));

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(3);
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
    public void consultAnimeSequalDetails_withPrequalAndSequal() throws Exception {
        AnimeDTO expectedAnimeDTO = newAnimeDTO()
                .nameEnglish("Steins;Gate: Egoistic Poriomania")
                .nameRomanized("Steins;Gate: Oukoubakko no Poriomania")
                .startAir(LocalDate.of(2012, 2, 22))
                .endAir(LocalDate.of(2012, 2, 22))
                .synopsis("Steins;Gate: Egoistic Poriomania synopsis here")
                .backdropUrl("http://backdrop.url.be/steins.gate.egoistic.poriomania")
                .posterUrl("http://poster.url.be/steins.gate.egoistic.poriomania")
                .build();

        performAndPrint(get(URL, 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", matchesAnimeDTO(expectedAnimeDTO)))
                .andExpect(jsonPath("$.links", containsResourceLinks(concatenate(EXPECTED_ANIME_DETAILS_LINKS, "prequal", "sequal"))));

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(3);
    }

    /**
     * When consulting an existing an Anime that has no sequal its details, it should return:
     * NOT FOUND http status
     * JSON content type
     * a JSON corresponding to an Error with status 'Not Found', code '404' and message saying 'Resource was not found'
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimeSequalDetailsServiceTest_consultAnimeSequalDetails.xml")
    public void consultAnimeSequalDetails_hasNoSequal() throws Exception {
        performAndPrint(get(URL, 3))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", matchesErrorDTO(RESOURCE_NOT_FOUND_ERROR_DTO)));

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    /**
     * When consulting a not existing Anime's details, it should return:
     * NOT FOUND http status
     * JSON content type
     * a JSON corresponding to an Error with status 'Not Found', code '404' and message saying 'Resource was not found'
     */
    @Test
    @DatabaseSetup("/com/korogi/rest/service/anime/ConsultAnimePrequalDetailsServiceTest_consultAnimePrequalDetails.xml")
    public void consultAnimeSequalDetails_notExisting() throws Exception {
        performAndPrint(get(URL, 99))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", matchesErrorDTO(RESOURCE_NOT_FOUND_ERROR_DTO)));

        hibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}