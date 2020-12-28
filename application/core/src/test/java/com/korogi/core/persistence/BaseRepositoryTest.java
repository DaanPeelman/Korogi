package com.korogi.core.persistence;

import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.korogi.core.config.DbUnitProstresqlConfig;
import com.korogi.core.config.RepositoryConfig;
import com.korogi.core.domain.BaseEntity;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;

@DataJpaTest
@ActiveProfiles("unit-test")
@ContextConfiguration(classes = { RepositoryConfig.class, DbUnitProstresqlConfig.class })
@EntityScan(basePackageClasses = BaseEntity.class)
@TestExecutionListeners(
    value = { DbUnitTestExecutionListener.class },
    mergeMode = MERGE_WITH_DEFAULTS
)
@AutoConfigureEmbeddedDatabase
public abstract class BaseRepositoryTest {

    @PersistenceContext
    protected EntityManager em;
}
