package com.korogi.core.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.korogi.core.config.CoreConfig;
import com.korogi.core.config.TestPersistenceConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Daan Peelman
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestPersistenceConfig.class, CoreConfig.class})
@Transactional
@TestExecutionListeners(
    {
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
    }
)
public abstract class BaseRepositoryTest {
    @PersistenceContext
    protected EntityManager em;
}