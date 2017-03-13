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
    @DatabaseSetup("/com/korogi/core/persistence/user/UserRepositoryTest_testFindById.xml")
    public void testFindById() throws Exception {
        long idToFind = 1;

        User foundUser = repository.findById(idToFind);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.id()).isEqualTo(idToFind);
    }

    /**
     * Should return null when no User with the given id was found to be present in the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/user/UserRepositoryTest_testFindById.xml")
    public void testFindById_notExisting() throws Exception {
        assertThat(repository.findById(99L)).isNull();
    }

    /**
     * Should save the passed User into the database and automatically set the creation date,
     * created by and version fields.
     */
    @Test
    @ExpectedDatabase(value = "/com/korogi/core/persistence/user/UserRepositoryTest_testSave_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void testSave() throws Exception {
        User userToSave = johnDoe();

        User savedUser = repository.saveOrUpdate(userToSave);

        em.flush();

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.id()).isNotNull();
        assertThat(savedUser.creationDate()).isNotNull();
        assertThat(savedUser.createdBy()).isNotNull();
        assertThat(savedUser.modificationDate()).isNull();
        assertThat(savedUser.modifiedBy()).isNull();
        assertThat(savedUser.version()).isNotNull();
    }

    /**
     * Should update the User in the database with the values in the updated user.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/user/UserRepositoryTest_testUpdate.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/user/UserRepositoryTest_testUpdate_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void testUpdate() throws Exception {
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
        assertThat(updatedUser.modificationDate()).isNotNull();
        assertThat(updatedUser.modifiedBy()).isNotNull();
    }

    /**
     * Should delete the User from the database.
     */
    @Test
    @DatabaseSetup("/com/korogi/core/persistence/user/UserRepositoryTest_testDelete.xml")
    @ExpectedDatabase(value = "/com/korogi/core/persistence/user/UserRepositoryTest_testDelete_result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void testDelete() throws Exception {
        User userToDelete = em.find(User.class, 1L);

        repository.delete(userToDelete);

        em.flush();
    }
}