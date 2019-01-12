package com.korogi.core.persistence.personage;

import static com.korogi.core.domain.testdata.PersonageTestData.okabeRintarou_notPersisted;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.korogi.core.domain.Anime;
import com.korogi.core.domain.Personage;
import com.korogi.core.persistence.BaseRepositoryTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonageRepositoryImplTest extends BaseRepositoryTest {
    @Autowired
    private PersonageRepositoryImpl repository;

    /**
     * Should retrieve the Personage with the same id from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/personage/PersonageRepositoryImplTest_findById.xml")
    public void findById() throws Exception {
        long idToFind = 1L;

        Personage foundPersonage = repository.findById(idToFind).orElse(null);

        assertThat(foundPersonage).isNotNull();
        assertThat(foundPersonage.getId()).isEqualTo(idToFind);
        assertThat(foundPersonage.getFirstName()).isNotNull().isEqualTo("Okabe");
        assertThat(foundPersonage.getLastName()).isNotNull().isEqualTo("Rintarou");
        assertThat(foundPersonage.getPhotoUrl()).isNotNull().isEqualTo("http://photo.url.be");

        assertThat(foundPersonage.getAnime()).isNotNull().hasSize(1).contains(em.find(Anime.class, 1L));
    }

    /**
     * Should return an empty optional when no Personage with the given id was found to be present in the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/personage/PersonageRepositoryImplTest_findById.xml")
    public void findById_notExisting() throws Exception {
        assertThat(repository.findById(99L)).isNotPresent();
    }

    /**
     * Should save the passed Personage into the database and automatically set the creation date,
     * created by and version fields.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/personage/PersonageRepositoryImplTest_save.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/personage/PersonageRepositoryImplTest_save_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void save() throws Exception {
        Anime anime = em.find(Anime.class, 1L);
        Personage personageToSave = okabeRintarou_notPersisted()
                .anime(singletonList(anime))
                .build();

        Personage savedPersonage = repository.saveOrUpdate(personageToSave);

        em.flush();

        assertThat(savedPersonage).isNotNull();
        assertThat(savedPersonage.getId()).isNotNull();
        assertThat(savedPersonage.getCreationDate()).isNotNull();
        assertThat(savedPersonage.getCreatedBy()).isNotNull();
        assertThat(savedPersonage.getModificationDate()).isNull();
        assertThat(savedPersonage.getModifiedBy()).isNull();
        assertThat(savedPersonage.getVersion()).isNotNull();
        assertThat(savedPersonage.getAnime()).isNotEmpty().hasSize(1).contains(anime);
    }

    /**
     * Should delete the Personage from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/personage/PersonageRepositoryImplTest_delete.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/personage/PersonageRepositoryImplTest_delete_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void delete() throws Exception {
        Personage personageToDelete = em.find(Personage.class, 1L);

        repository.delete(personageToDelete);

        em.flush();
    }
}