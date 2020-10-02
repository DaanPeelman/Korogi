package com.korogi.core.domain;

import static com.korogi.core.domain.Assertions.assertThat;
import static com.korogi.core.domain.enumeration.AnimeType.TV;
import static com.korogi.core.domain.testdata.AnimeTestData.steinsGate_notPersisted;
import static java.time.Month.DECEMBER;

import java.time.LocalDate;
import com.korogi.core.domain.enumeration.AnimeType;
import org.junit.jupiter.api.Test;

/**
 * @author Daan Peelman
 */
class AnimeTest {

    /**
     * Should create a new anime with the values passed to the builder.
     */
    @Test
    void newAnime_new() throws Exception {
        AnimeType animeType = TV;
        String nameEnglish = "English name";
        String nameRomanized = "Romanized name";
        LocalDate startAir = LocalDate.of(2017, DECEMBER, 2);
        LocalDate endAir = LocalDate.of(2017, DECEMBER, 3);
        String synopsis = "Synopsis";
        Anime prequal = steinsGate_notPersisted().build();

        Anime anime = Anime.newAnime()
                .animeType(animeType)
                .nameEnglish(nameEnglish)
                .nameRomanized(nameRomanized)
                .startAir(startAir)
                .endAir(endAir)
                .synopsis(synopsis)
                .prequal(prequal)
                .build();

        assertThat(anime)
                .isNotNull()
                .hasAnimeType(animeType)
                .hasNameEnglish(nameEnglish)
                .hasNameRomanized(nameRomanized)
                .hasStartAir(startAir)
                .hasSynopsis(synopsis)
                .hasPrequal(prequal);
        assertThat(prequal).hasSequal(anime);
    }
}