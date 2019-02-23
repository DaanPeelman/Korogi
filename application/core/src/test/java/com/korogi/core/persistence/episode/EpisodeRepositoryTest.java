package com.korogi.core.persistence.episode;

import static com.korogi.core.domain.testdata.EpisodeTestData.steinsGateEpisode1_notPeristed;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.korogi.core.domain.Anime;
import com.korogi.core.domain.Episode;
import com.korogi.core.persistence.BaseRepositoryTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EpisodeRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private EpisodeRepository repository;

    /**
     * Should retrieve the Episode with the same id from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/episode/EpisodeRepositoryTest_findById.xml")
    public void findById() throws Exception {
        long idToFind = 1L;

        Episode foundEpisode = repository.findById(idToFind).orElse(null);

        assertThat(foundEpisode).isNotNull();
        assertThat(foundEpisode.getId()).isEqualTo(idToFind);
        assertThat(foundEpisode.getAnime()).isNotNull().isEqualTo(em.find(Anime.class, 1L));
        assertThat(foundEpisode.getName()).isNotNull().isEqualTo("Prologue of the Beginning and Ending");
        assertThat(foundEpisode.getSynopsis()).isNotNull().isEqualTo("Prologue of the Beginning and Ending synopsis here");
        assertThat(foundEpisode.getDurationInMinutes()).isNotNull().isEqualTo(24);
        assertThat(foundEpisode.getAirDate()).isNotNull().isEqualTo(LocalDate.of(2011, 4, 5));
    }

    /**
     * Should return an empty optional when no Episode with the given id was found to be present in the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/episode/EpisodeRepositoryTest_findById.xml")
    public void findById_notExisting() throws Exception {
        assertThat(repository.findById(99L)).isNotPresent();
    }

    /**
     * Should save the passed Episode into the database and automatically set the creation date,
     * created by and version fields.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/episode/EpisodeRepositoryTest_save.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/episode/EpisodeRepositoryTest_save_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void save() throws Exception {
        Anime anime = em.find(Anime.class, 1L);
        Episode episodeToSave = steinsGateEpisode1_notPeristed()
                .anime(anime)
                .build();

        Episode savedEpisode = repository.save(episodeToSave);

        em.flush();

        assertThat(savedEpisode).isNotNull();
        assertThat(savedEpisode.getId()).isNotNull();
        assertThat(savedEpisode.getCreationDate()).isNotNull();
        assertThat(savedEpisode.getCreatedBy()).isNotNull();
        assertThat(savedEpisode.getModificationDate()).isNull();
        assertThat(savedEpisode.getModifiedBy()).isNull();
        assertThat(savedEpisode.getVersion()).isNotNull();
        assertThat(savedEpisode.getAnime()).isEqualTo(anime);

        assertThat(anime.getEpisodes()).contains(savedEpisode);
    }

    /**
     * Should delete the Episode from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/episode/EpisodeRepositoryTest_delete.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/episode/EpisodeRepositoryTest_delete_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void delete() throws Exception {
        Episode episodeToDelete = em.find(Episode.class, 1L);

        repository.delete(episodeToDelete);

        em.flush();
    }
}