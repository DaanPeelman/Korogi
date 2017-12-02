package com.korogi.core.domain;

import static com.korogi.core.domain.Anime.newAnime;
import static com.korogi.core.domain.enumeration.AnimeType.SPECIAL;
import static com.korogi.core.domain.enumeration.AnimeType.TV;
import static com.korogi.core.domain.mother.AnimeMother.clannad;
import static com.korogi.core.domain.mother.AnimeMother.steinsGate;
import static com.korogi.core.domain.mother.AnimeMother.steinsGate_withPrequal_updated;
import static java.time.Month.DECEMBER;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.korogi.core.domain.enumeration.AnimeType;
import com.korogi.core.validation.ValidationException;
import org.junit.Test;

/**
 * @author Daan Peelman
 */
public class AnimeTest {
    /**
     * Should validate when using builder.
     */
    @Test
    public void validate() {
        try {
            newAnime().build();
            fail("expected ValidationException to have been thrown");
        } catch (ValidationException e) {
            assertThat(e.getConstraintViolations())
                    .isNotNull()
                    .hasSize(4);
            assertThat(e.getMessage())
                    .isNotNull()
                    .contains("animeType")
                    .contains("nameEnglish")
                    .contains("nameRomanized")
                    .contains("synopsis");
        }
    }

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
        Anime prequal = steinsGate();

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
//
    /**
     * Should create a new anime instance with the exact same field values as the original anime.
     */
    @Test
    public void newRole_copy() throws Exception {
        Anime originalAnime = steinsGate_withPrequal_updated();
        Anime originalPrequal = originalAnime.getPrequal();

        Anime copiedAnime = newAnime(originalAnime).build();
        Anime copiedAnimePrequal = copiedAnime.getPrequal();

        assertThat(copiedAnime).isNotNull().isNotSameAs(originalAnime);
        assertThat(copiedAnime.getAnimeType()).isNotNull().isEqualTo(originalAnime.getAnimeType());
        assertThat(copiedAnime.getNameEnglish()).isNotNull().isEqualTo(originalAnime.getNameEnglish());
        assertThat(copiedAnime.getNameRomanized()).isNotNull().isEqualTo(originalAnime.getNameRomanized());
        assertThat(copiedAnime.getStartAir()).isNotNull().isEqualTo(originalAnime.getStartAir());
        assertThat(copiedAnime.getEndAir()).isNotNull().isEqualTo(originalAnime.getEndAir());
        assertThat(copiedAnime.getSynopsis()).isNotNull().isEqualTo(originalAnime.getSynopsis());
        assertThat(copiedAnime.getId()).isNotNull().isEqualTo(originalAnime.getId());
        assertThat(copiedAnime.getCreationDate()).isNotNull().isEqualTo(originalAnime.getCreationDate());
        assertThat(copiedAnime.getCreatedBy()).isNotNull().isEqualTo(originalAnime.getCreatedBy());
        assertThat(copiedAnime.getModificationDate()).isNotNull().isEqualTo(originalAnime.getModificationDate());
        assertThat(copiedAnime.getModifiedBy()).isNotNull().isEqualTo(originalAnime.getModifiedBy());
        assertThat(copiedAnime.getVersion()).isNotNull().isEqualTo(originalAnime.getVersion());

        assertThat(originalPrequal).isSameAs(copiedAnimePrequal);
        assertThat(copiedAnimePrequal.getSequal()).isSameAs(copiedAnime);
    }

    /**
     * Should create a new anime, based on the old one but with the updated values that are passed
     * to the builder.
     */
    @Test
    public void newRole_update() throws Exception {
        Anime originalAnime = steinsGate_withPrequal_updated();

        AnimeType originalAnimeType = originalAnime.getAnimeType();
        String originalNameEnglish = originalAnime.getNameEnglish();
        String originalNameRomanized = originalAnime.getNameRomanized();
        LocalDate originalStartAir = originalAnime.getStartAir();
        LocalDate originalEndAir = originalAnime.getEndAir();
        String originalSynopsis = originalAnime.getSynopsis();
        Long originalId = originalAnime.getId();
        LocalDateTime originalCreationDate = originalAnime.getCreationDate();
        String originalCreatedBy = originalAnime.getCreatedBy();
        LocalDateTime originalModificationDate = originalAnime.getModificationDate();
        String originalModifiedBy = originalAnime.getModifiedBy();
        Long originalVersion = originalAnime.getVersion();

        AnimeType newAnimeType = SPECIAL;
        String newNameEnglish = "new name English";
        String newNameRomanized = "new name Romanized";
        LocalDate newStartAir = LocalDate.of(2017, DECEMBER, 25);
        LocalDate newEndAir = LocalDate.of(2017, DECEMBER, 26);
        String newSynopsis = "new synopsis";

        Anime newPrequal = clannad();

        Anime updatedRole = Anime.newAnime(originalAnime)
                .animeType(newAnimeType)
                .nameEnglish(newNameEnglish)
                .nameRomanized(newNameRomanized)
                .startAir(newStartAir)
                .endAir(newEndAir)
                .synopsis(newSynopsis)
                .prequal(newPrequal)
                .build();

        assertThat(updatedRole).isNotNull().isNotSameAs(originalAnime);
        assertThat(updatedRole.getAnimeType()).isNotNull().isEqualTo(newAnimeType);
        assertThat(updatedRole.getNameEnglish()).isNotNull().isEqualTo(newNameEnglish);
        assertThat(updatedRole.getNameRomanized()).isNotNull().isEqualTo(newNameRomanized);
        assertThat(updatedRole.getStartAir()).isNotNull().isEqualTo(newStartAir);
        assertThat(updatedRole.getEndAir()).isNotNull().isEqualTo(newEndAir);
        assertThat(updatedRole.getSynopsis()).isNotNull().isEqualTo(newSynopsis);
        assertThat(updatedRole.getId()).isNotNull().isEqualTo(originalAnime.getId()).isEqualTo(originalId);
        assertThat(updatedRole.getCreationDate()).isNotNull().isEqualTo(originalAnime.getCreationDate()).isEqualTo(originalCreationDate);
        assertThat(updatedRole.getCreatedBy()).isNotNull().isEqualTo(originalAnime.getCreatedBy()).isEqualTo(originalCreatedBy);
        assertThat(updatedRole.getModificationDate()).isNotNull().isEqualTo(originalAnime.getModificationDate()).isEqualTo(originalModificationDate);
        assertThat(updatedRole.getModifiedBy()).isNotNull().isEqualTo(originalAnime.getModifiedBy()).isEqualTo(originalModifiedBy);
        assertThat(updatedRole.getVersion()).isNotNull().isEqualTo(originalAnime.getVersion()).isEqualTo(originalVersion);
        assertThat(originalAnime.getAnimeType()).isEqualTo(originalAnimeType);
        assertThat(originalAnime.getNameEnglish()).isEqualTo(originalNameEnglish);
        assertThat(originalAnime.getNameRomanized()).isEqualTo(originalNameRomanized);
        assertThat(originalAnime.getStartAir()).isEqualTo(originalStartAir);
        assertThat(originalAnime.getEndAir()).isEqualTo(originalEndAir);
        assertThat(originalAnime.getSynopsis()).isEqualTo(originalSynopsis);

        assertThat(updatedRole.getPrequal()).isNotNull().isEqualTo(newPrequal);
        assertThat(newPrequal.getSequal()).isNotNull().isEqualTo(updatedRole);
    }
}