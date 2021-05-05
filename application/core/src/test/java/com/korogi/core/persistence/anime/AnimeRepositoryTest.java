package com.korogi.core.persistence.anime;

import static com.korogi.core.domain.testdata.AnimeTestData.steinsGate_notPersisted;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.korogi.core.domain.Anime;
import com.korogi.core.domain.Personage;
import com.korogi.core.persistence.BaseRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AnimeRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private AnimeRepository repository;

    /**
     * Should retrieve the Anime with the same id from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_findById.xml")
    void findById_withSequal() {
        long idToFind = 1L;

        Anime foundAnime = repository.findById(idToFind).orElse(null);

        assertThat(foundAnime).isNotNull();
        assertThat(foundAnime.getId()).isEqualTo(idToFind);
        assertThat(foundAnime.getSequal()).isEqualTo(em.find(Anime.class, 2L));
        assertThat(foundAnime.getPrequal()).isNull();
    }

    /**
     * Should retrieve the Anime with the same id from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_findById.xml")
    void findById_withPrequal() {
        long idToFind = 3L;

        Anime foundAnime = repository.findById(idToFind).orElse(null);

        assertThat(foundAnime).isNotNull();
        assertThat(foundAnime.getId()).isEqualTo(idToFind);
        assertThat(foundAnime.getPrequal()).isEqualTo(em.find(Anime.class, 2L));
        assertThat(foundAnime.getSequal()).isNull();
    }

    /**
     * Should return an empty optional when no Anime with the given id was found to be present in the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_findById.xml")
    void findById_notExisting() {
        assertThat(repository.findById(99L)).isNotPresent();
    }

    /**
     * Should save the passed Anime into the database and automatically set the creation date,
     * created by and version fields.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_save.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/anime/AnimeRepositoryTest_save_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void save() {
        Personage personage = em.find(Personage.class, 1L);

        Anime animeToSave = steinsGate_notPersisted()
            .personages(singletonList(personage))
            .build();

        Anime savedAnime = repository.save(animeToSave);

        em.flush();

        assertThat(savedAnime).isNotNull();
        assertThat(savedAnime.getId()).isNotNull();
        assertThat(savedAnime.getCreationDate()).isNotNull();
        assertThat(savedAnime.getCreatedBy()).isNotNull();
        assertThat(savedAnime.getModificationDate()).isNull();
        assertThat(savedAnime.getModifiedBy()).isNull();
        assertThat(savedAnime.getVersion()).isNotNull();

        assertThat(savedAnime.getPersonages()).containsOnly(personage);
        assertThat(personage.getAnime()).containsOnly(savedAnime);
    }

    /**
     * Should delete the Anime from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_delete.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/anime/AnimeRepositoryTest_delete_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void delete() throws Exception {
        Anime animeToDelete = em.find(Anime.class, 1L);

        repository.delete(animeToDelete);

        em.flush();
    }

    /**
     * Should retrieve the Anime that is a prequal to the anime with the passed id from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_findPrequalOfAnime.xml")
    void findPrequalOfAnime() throws Exception {
        Anime animeToFind = em.find(Anime.class, 1L);

        assertThat(repository.findPrequalOfAnime(2L))
            .isPresent()
            .contains(animeToFind);
    }

    /**
     * Should return an empty optional if the Anime with the passed id does not have a prequal linked to it in the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_findPrequalOfAnime.xml")
    void findPrequalOfAnime_hasNoPrequal() throws Exception {
        assertThat(repository.findPrequalOfAnime(1L)).isNotPresent();
    }

    /**
     * Should return an empty optional when no Anime with the given id was found to be present in the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_findPrequalOfAnime.xml")
    void findPrequalOfAnime_notExisting() throws Exception {
        assertThat(repository.findPrequalOfAnime(99L)).isNotPresent();
    }

    /**
     * Should retrieve the Anime that is a sequal to the anime with the passed id from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_findSequalOfAnime.xml")
    void findSequalOfAnime() throws Exception {
        Anime animeToFind = em.find(Anime.class, 2L);

        assertThat(repository.findSequalOfAnime(1L))
            .isPresent()
            .contains(animeToFind);
    }

    /**
     * Should return an empty optional if the Anime with the passed id does not have a sequal linked to it in the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_findSequalOfAnime.xml")
    void findSequalOfAnime_hasNoSequal() throws Exception {
        assertThat(repository.findSequalOfAnime(2L)).isNotPresent();
    }

    /**
     * Should return an empty optional when no Anime with the given id was found to be present in the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/anime/AnimeRepositoryTest_findSequalOfAnime.xml")
    void findSequalOfAnime_notExisting() throws Exception {
        assertThat(repository.findPrequalOfAnime(99L)).isNotPresent();
    }
}