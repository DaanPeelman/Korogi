package com.korogi.core.persistence.role;

import static com.korogi.core.domain.Assertions.assertThat;
import static com.korogi.core.domain.testdata.RoleTestData.admin;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.korogi.core.domain.Role;
import com.korogi.core.persistence.BaseRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Daan Peelman
 */
class RoleRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private RoleRepository repository;

    /**
     * Should retrieve the Role with the same id from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/role/RoleRepositoryTest_findById.xml")
    void findById() throws Exception {
        long idToFind = 1;

        Role foundRole = repository.findById(idToFind).orElse(null);

        assertThat(foundRole)
                .isNotNull()
                .hasId(idToFind);
    }

    /**
     * Should return null when no Role with the given id was found to be present in the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/role/RoleRepositoryTest_findById.xml")
    void findById_notExisting() throws Exception {
        assertThat(repository.findById(99L)).isNotPresent();
    }

    /**
     * Should save the passed Role into the database and automatically set the creation date,
     * created by and version fields.
     */
    @Test
    @ExpectedDatabase(value = "/com/korogi/core/persistence/role/RoleRepositoryTest_save_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void save() throws Exception {
        Role roleToSave = admin().build();

        Role savedRole = repository.save(roleToSave);

        em.flush();

        assertThat(savedRole).isNotNull();
        assertThat(savedRole.getId()).isNotNull();
        assertThat(savedRole.getCreationDate()).isNotNull();
        assertThat(savedRole.getCreatedBy()).isNotNull();
        assertThat(savedRole.getModificationDate()).isNull();
        assertThat(savedRole.getModifiedBy()).isNull();
        assertThat(savedRole.getVersion()).isNotNull();
    }

    /**
     * Should delete the Role from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/role/RoleRepositoryTest_delete.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/role/RoleRepositoryTest_delete_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void delete() throws Exception {
        Role roleToDelete = em.find(Role.class, 1L);

        repository.delete(roleToDelete);

        em.flush();
    }
}