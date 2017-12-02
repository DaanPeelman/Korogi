package com.korogi.core.domain.builder;

import static com.korogi.core.domain.Anime.newAnime;

import java.time.LocalDate;
import com.korogi.core.domain.Anime;
import com.korogi.core.domain.Anime.AnimeBuilder;
import com.korogi.core.domain.enumeration.AnimeType;

/**
 * Builder class for building test-<code>Anime</code> entities.<br />
 * <br />
 * Using this builder allows for setting otherwise immutable fields.
 *
 * @author Daan Peelman
 *
 * @see Anime
 * @see AnimeBuilder
 * @see BaseTestEntityBuilder
 */
public class TestAnimeBuilder extends BaseTestEntityBuilder<Anime, AnimeBuilder, TestAnimeBuilder> {
    public static TestAnimeBuilder newTestAnime() {
        return new TestAnimeBuilder();
    }

    public static TestAnimeBuilder newTestAnime(Anime anime) {
        return new TestAnimeBuilder(anime);
    }

    private TestAnimeBuilder() {
        super(newAnime());
        setTestEntityBuilder(this);
    }

    private TestAnimeBuilder(Anime anime) {
        super(newAnime(anime));
        setTestEntityBuilder(this);
    }

    public TestAnimeBuilder animeType(AnimeType animeType) {
        entityBuilder.animeType(animeType);
        return this;
    }

    public TestAnimeBuilder nameEnglish(String nameEnglish) {
        entityBuilder.nameEnglish(nameEnglish);
        return this;
    }

    public TestAnimeBuilder nameRomanized(String nameRomanized) {
        entityBuilder.nameRomanized(nameRomanized);
        return this;
    }

    public TestAnimeBuilder startAir(LocalDate startAir) {
        entityBuilder.startAir(startAir);
        return this;
    }

    public TestAnimeBuilder endAir(LocalDate endAir) {
        entityBuilder.endAir(endAir);
        return this;
    }

    public TestAnimeBuilder synopsis(String synopsis) {
        entityBuilder.synopsis(synopsis);
        return this;
    }

    public TestAnimeBuilder prequal(Anime prequal) {
        entityBuilder.prequal(prequal);
        return this;
    }

    public TestAnimeBuilder sequal(Anime sequal) {
        entityBuilder.sequal(sequal);
        return this;
    }
}