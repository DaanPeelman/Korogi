package com.korogi.rest.restservice.anime;

import static com.korogi.core.domain.enumeration.AnimeType.TV;
import static com.korogi.rest.util.CustomContentResultMatchers.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.korogi.rest.BaseServiceTest;
import com.korogi.rest.util.HibernateStatisticsUtil;
import org.junit.jupiter.api.Test;

class ConsultAnimeServiceTest extends BaseServiceTest {
    private static final String URL = "/anime";

    @Test
    @DatabaseSetup("/com/korogi/rest/restservice/anime/ConsultAnimeServiceTest_consultAnime.xml")
    void consultAnime_withoutQueryParams() throws Exception {
        performAndPrint(get(URL))
            .andExpect(status().isOk())
            .andExpect(json().matchesFileContent(
                "com/korogi/rest/restservice/anime/ConsultAnimeServiceTest_consultAnime_withoutQueryParams_expected.json"));

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    @Test
    @DatabaseSetup("/com/korogi/rest/restservice/anime/ConsultAnimeServiceTest_consultAnime.xml")
    void consultAnime_withNameQueryParam_multipleResults() throws Exception {
        performAndPrint(get(URL).param("name", "Steins"))
            .andExpect(status().isOk())
            .andExpect(json().matchesFileContent(
                "com/korogi/rest/restservice/anime/ConsultAnimeServiceTest_consultAnime_withNameQueryParam_multipleResults_expected.json"));

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    @Test
    @DatabaseSetup("/com/korogi/rest/restservice/anime/ConsultAnimeServiceTest_consultAnime.xml")
    void consultAnime_withNameQueryParams_singleResult() throws Exception {
        performAndPrint(get(URL).param("name", "Shinse"))
            .andExpect(status().isOk())
            .andExpect(json().matchesFileContent(
                "com/korogi/rest/restservice/anime/ConsultAnimeServiceTest_consultAnime_withNameQueryParam_singleResult_expected.json"));

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    @Test
    @DatabaseSetup("/com/korogi/rest/restservice/anime/ConsultAnimeServiceTest_consultAnime.xml")
    void consultAnime_withAnimeTypeQueryParams_singleResult() throws Exception {
        performAndPrint(get(URL).param("animeType", TV.name()))
            .andExpect(status().isOk())
            .andExpect(json().matchesFileContent(
                "com/korogi/rest/restservice/anime/ConsultAnimeServiceTest_consultAnime_withAnimeTypeQueryParam_singleResult_expected.json"));

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }

    @Test
    @DatabaseSetup("/com/korogi/rest/restservice/anime/ConsultAnimeServiceTest_consultAnime.xml")
    void consultAnime_withAllQueryParams() throws Exception {
        performAndPrint(
            get(URL).param("name", "Steins")
                    .param("animeType", TV.name())
        ).andExpect(status().isOk())
         .andExpect(json().matchesFileContent(
             "com/korogi/rest/restservice/anime/ConsultAnimeServiceTest_consultAnime_withAllQueryParams_expected.json"));

        HibernateStatisticsUtil.assertAmountOfQuerriesExecuted(1);
    }
}