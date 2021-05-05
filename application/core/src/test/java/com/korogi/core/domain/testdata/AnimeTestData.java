package com.korogi.core.domain.testdata;

import static com.korogi.core.domain.Anime.newAnime;
import static com.korogi.core.domain.enumeration.AnimeType.TV;
import static java.time.LocalDate.of;
import static java.time.Month.APRIL;
import static java.time.Month.SEPTEMBER;

import com.korogi.core.domain.Anime;

/**
 * @author Daan Peelman
 */
public class AnimeTestData {
    public static Anime.AnimeBuilder steinsGate_notPersisted() {
        return newAnime()
            .animeType(TV)
            .nameEnglish("Steins;Gate")
            .nameRomanized("Steins;Gate")
            .startAir(of(2011, APRIL, 6))
            .endAir(of(2011, SEPTEMBER, 14))
            .synopsis("Steins;Gate synopsis")
            .backdropUrl("http://backdrop.url.be/steins.gate")
            .posterUrl("http://poster.url.be/stains.gate");
    }
}