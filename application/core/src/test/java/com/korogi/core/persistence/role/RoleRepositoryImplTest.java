package com.korogi.core.persistence.role;

import static com.korogi.core.domain.enumeration.RoleType.USER;
import static com.korogi.core.domain.mother.RoleMother.admin;
import static org.fest.assertions.Assertions.assertThat;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.korogi.core.domain.Role;
import com.korogi.core.persistence.BaseRepositoryTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Daan Peelman
 */
public class RoleRepositoryImplTest extends BaseRepositoryTest {
    @Autowired
    private RoleRepositoryImpl repository;

    /**
     * Should retrieve the Role with the same id from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/role/RoleRepositoryTest_findById.xml")
    public void findById() throws Exception {
        long idToFind = 1;

        Role foundRole = repository.findById(idToFind);

        assertThat(foundRole).isNotNull();
        assertThat(foundRole.getId()).isEqualTo(idToFind);
    }

    /**
     * Should return null when no Role with the given id was found to be present in the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/role/RoleRepositoryTest_findById.xml")
    public void findById_notExisting() throws Exception {
        assertThat(repository.findById(99L)).isNull();
    }

    /**
     * Should save the passed Role into the database and automatically set the creation date,
     * created by and version fields.
     */
    @Test
    @ExpectedDatabase(value = "/com/korogi/core/persistence/role/RoleRepositoryTest_save_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void save() throws Exception {
        Role roleToSave = admin();

        Role savedRole = repository.saveOrUpdate(roleToSave);

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
     * Should update the Role in the database with the values in the updated Role.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/role/RoleRepositoryTest_update.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/role/RoleRepositoryTest_update_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void update() throws Exception {
        Role originalRole = em.find(Role.class, 1L);

        Role roleToUpdate = Role.newRole(originalRole)
                .roleType(USER)
                .build();

        Role updatedRole = repository.saveOrUpdate(roleToUpdate);

        em.flush();

        assertThat(updatedRole).isNotNull();
        assertThat(updatedRole.getModificationDate()).isNotNull();
        assertThat(updatedRole.getModifiedBy()).isNotNull();
    }

    /**
     * Should delete the Role from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/role/RoleRepositoryTest_delete.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/role/RoleRepositoryTest_delete_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void delete() throws Exception {
        Role roleToDelete = em.find(Role.class, 1L);

        repository.delete(roleToDelete);

        em.flush();
    }
}