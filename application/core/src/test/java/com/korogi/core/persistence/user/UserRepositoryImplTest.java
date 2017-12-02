package com.korogi.core.persistence.user;

import static com.korogi.core.domain.User.newUser;
import static com.korogi.core.domain.mother.UserMother.johnDoe;
import static org.fest.assertions.Assertions.assertThat;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.korogi.core.domain.User;
import com.korogi.core.persistence.BaseRepositoryTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Daan Peelman
 */
public class UserRepositoryImplTest extends BaseRepositoryTest {
    @Autowired
    private UserRepositoryImpl repository;

    /**
     * Should retrieve the User with the same id from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/user/UserRepositoryTest_findById.xml")
    public void findById() throws Exception {
        long idToFind = 1;

        User foundUser = repository.findById(idToFind);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(idToFind);
    }

    /**
     * Should return null when no User with the given id was found to be present in the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/user/UserRepositoryTest_findById.xml")
    public void findById_notExisting() throws Exception {
        assertThat(repository.findById(99L)).isNull();
    }

    /**
     * Should save the passed User into the database and automatically set the creation date,
     * created by and version fields.
     */
    @Test
    @ExpectedDatabase(value = "/com/korogi/core/persistence/user/UserRepositoryTest_save_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void save() throws Exception {
        User userToSave = johnDoe();

        User savedUser = repository.saveOrUpdate(userToSave);

        em.flush();

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getCreationDate()).isNotNull();
        assertThat(savedUser.getCreatedBy()).isNotNull();
        assertThat(savedUser.getModificationDate()).isNull();
        assertThat(savedUser.getModifiedBy()).isNull();
        assertThat(savedUser.getVersion()).isNotNull();
    }

    /**
     * Should update the User in the database with the values in the updated user.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/user/UserRepositoryTest_update.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/user/UserRepositoryTest_update_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void update() throws Exception {
        User originalUser = em.find(User.class, 1L);

        User userToUpdate = newUser(originalUser)
                .email("john.doe.updated@test.com")
                .username("john.doe.updated")
                .password("password.updated")
                .activate()
                .build();

        User updatedUser = repository.saveOrUpdate(userToUpdate);

        em.flush();

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getModificationDate()).isNotNull();
        assertThat(updatedUser.getModifiedBy()).isNotNull();
    }

    /**
     * Should delete the User from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/user/UserRepositoryTest_delete.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/user/UserRepositoryTest_delete_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void delete() throws Exception {
        User userToDelete = em.find(User.class, 1L);

        repository.delete(userToDelete);

        em.flush();
    }
}