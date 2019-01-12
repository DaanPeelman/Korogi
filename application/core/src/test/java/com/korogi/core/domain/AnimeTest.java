package com.korogi.core.domain;

import static com.korogi.core.domain.enumeration.AnimeType.TV;
import static com.korogi.core.domain.testdata.AnimeTestData.steinsGate_notPersisted;
import static java.time.Month.DECEMBER;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import com.korogi.core.domain.enumeration.AnimeType;
import org.junit.Test;

/**
 * @author Daan Peelman
 */
public class AnimeTest {

    /**
     * Should create a new anime with the values passed to the builder.
     */
    @Test
    public void newAnime_new() throws Exception {
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

        assertThat(anime).isNotNull();
        assertThat(anime.getAnimeType()).isEqualTo(animeType);
        assertThat(anime.getNameEnglish()).isEqualTo(nameEnglish);
        assertThat(anime.getNameRomanized()).isEqualTo(nameRomanized);
        assertThat(anime.getStartAir()).isEqualTo(startAir);
        assertThat(anime.getEndAir()).isEqualTo(endAir);
        assertThat(anime.getSynopsis()).isEqualTo(synopsis);
        assertThat(anime.getPrequal()).isEqualTo(prequal);
        assertThat(prequal.getSequal()).isEqualTo(anime);
    }
}