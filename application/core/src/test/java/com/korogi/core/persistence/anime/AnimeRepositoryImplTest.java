package com.korogi.core.persistence.anime;

import static com.korogi.core.domain.Anime.newAnime;
import static com.korogi.core.domain.enumeration.AnimeType.TV;
import static com.korogi.core.domain.mother.AnimeMother.steinsGate;
import static java.time.LocalDate.of;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static org.fest.assertions.Assertions.assertThat;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.korogi.core.domain.Anime;
import com.korogi.core.persistence.BaseRepositoryTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AnimeRepositoryImplTest extends BaseRepositoryTest {
    @Autowired
    private AnimeRepositoryImpl repository;

    /**
     * Should retrieve the Anime with the same id from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_findById.xml")
    public void findById_withSequal() {
        long idToFind = 1L;

        Anime foundAnime = repository.findById(idToFind);

        assertThat(foundAnime).isNotNull();
        assertThat(foundAnime.getId()).isEqualTo(idToFind);

        assertThat(foundAnime.getSequal()).isNotNull();
        assertThat(foundAnime.getSequal().getId()).isEqualTo(2L);

        assertThat(foundAnime.getPrequal()).isNull();
    }

    /**
     * Should retrieve the Anime with the same id from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_findById.xml")
    public void findById_withPrequal() {
        long idToFind = 3L;

        Anime foundAnime = repository.findById(idToFind);

        assertThat(foundAnime).isNotNull();
        assertThat(foundAnime.getId()).isEqualTo(idToFind);

        assertThat(foundAnime.getPrequal()).isNotNull();
        assertThat(foundAnime.getPrequal().getId()).isEqualTo(2L);

        assertThat(foundAnime.getSequal()).isNull();
    }

    /**
     * Should return null when no Anime with the given id was found to be present in the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_findById.xml")
    public void findById_notExisting() {
        assertThat(repository.findById(99L)).isNull();
    }

    /**
     * Should save the passed Anime into the database and automatically set the creation date,
     * created by and version fields.
     */
    @Test
    @ExpectedDatabase(value = "/com/korogi/core/persistence/anime/AnimeRepositoryTest_save_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void save() {
        Anime animeToSave = steinsGate();

        Anime savedAnime = repository.saveOrUpdate(animeToSave);

        em.flush();

        assertThat(savedAnime).isNotNull();
        assertThat(savedAnime.getId()).isNotNull();
        assertThat(savedAnime.getCreationDate()).isNotNull();
        assertThat(savedAnime.getCreatedBy()).isNotNull();
        assertThat(savedAnime.getModificationDate()).isNull();
        assertThat(savedAnime.getModifiedBy()).isNull();
        assertThat(savedAnime.getVersion()).isNotNull();
    }

    /**
     * Should update the Anime in the database with the values in the updated Anime.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_update.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/anime/AnimeRepositoryTest_update_withPrequal_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void update_withPrequal() {
        Anime originalAnime = em.find(Anime.class, 2L);

        Anime animeToUpdate = newAnime(originalAnime)
                .animeType(TV)
                .nameEnglish("Steins;Gate: Egoistic Poriomania updated")
                .nameRomanized("Steins;Gate: Oukoubakko no Poriomania updated")
                .startAir(of(2017, DECEMBER, 2))
                .endAir(of(2018, JANUARY, 1))
                .synopsis("Steins;Gate: Egoistic Poriomania synopsis updated")
                .prequal(em.find(Anime.class, 1L))
                .build();

        Anime updatedAnime = repository.saveOrUpdate(animeToUpdate);

        em.flush();

        assertThat(updatedAnime).isNotNull();
        assertThat(updatedAnime.getModificationDate()).isNotNull();
        assertThat(updatedAnime.getModifiedBy()).isNotNull();
    }

    /**
     * Should update the Anime in the database with the values in the updated Anime.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_update.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/anime/AnimeRepositoryTest_update_withSequal_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void update_withSequal() {
        Anime originalAnime = em.find(Anime.class, 1L);

        Anime animeToUpdate = newAnime(originalAnime)
                .sequal(em.find(Anime.class, 2L))
                .build();

        Anime updatedAnime = repository.saveOrUpdate(animeToUpdate);

        em.flush();

        assertThat(updatedAnime).isNotNull();
        assertThat(updatedAnime.getModificationDate()).isNotNull();
        assertThat(updatedAnime.getModifiedBy()).isNotNull();
    }

    /**
     * Should delete the Anime from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_delete.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/anime/AnimeRepositoryTest_delete_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void delete() throws Exception {
        Anime animeToDelete = em.find(Anime.class, 1L);

        repository.delete(animeToDelete);

        em.flush();
    }
}