package com.korogi.core.domain.mother;

import static com.korogi.core.domain.builder.TestAnimeBuilder.newTestAnime;
import static com.korogi.core.domain.enumeration.AnimeType.TV;
import static java.time.LocalDate.of;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.MARCH;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

import com.korogi.core.domain.Anime;

/**
 * @author Daan Peelman
 */
public class AnimeMother {
    public static Anime steinsGate() {
        return newTestAnime()
                .animeType(TV)
                .nameEnglish("Steins;Gate")
                .nameRomanized("Steins;Gate")
                .startAir(of(2011, APRIL, 6))
                .endAir(of(2011, SEPTEMBER, 14))
                .synopsis("Steins;Gate synopsis")
                .build();
    }

    public static Anime steinsGate_withPrequal_updated() {
        return newTestAnime(steinsGate())
                .id(1L)
                .creationDate(of(2017, DECEMBER, 2).atStartOfDay())
                .createdBy("john.doe")
                .modificationDate(of(2017, DECEMBER, 3).atStartOfDay())
                .modifiedBy("john.doe")
                .version(2L)
                .prequal(steinsGate_prequal())
                .build();
    }

    public static Anime steinsGate_prequal() {
        return newTestAnime()
                .animeType(TV)
                .nameEnglish("Steins;Gate prequal")
                .nameRomanized("Steins;Gate prequal")
                .startAir(of(2011, APRIL, 2))
                .endAir(of(2011, SEPTEMBER, 1))
                .synopsis("Steins;Gate prequal synopsis")
                .id(2L)
                .creationDate(of(2017, DECEMBER, 2).atStartOfDay())
                .createdBy("john.doe")
                .version(1L)
                .build();
    }

    public static Anime clannad() {
        return newTestAnime()
                .animeType(TV)
                .nameEnglish("Clannad")
                .nameRomanized("Clannad")
                .startAir(of(2007, OCTOBER, 5))
                .endAir(of(2008, MARCH, 28))
                .synopsis("Clannad synopsis")
                .id(3L)
                .creationDate(of(2017, DECEMBER, 2).atStartOfDay())
                .createdBy("john.doe")
                .version(1L)
                .build();
    }
}