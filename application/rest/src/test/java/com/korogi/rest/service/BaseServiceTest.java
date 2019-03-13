package com.korogi.rest.service;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.korogi.core.config.CoreConfig;
import com.korogi.core.config.TestPersistenceConfig;
import com.korogi.core.interceptor.HibernateStatisticsInterceptor;
import com.korogi.rest.config.RestConfig;
import com.korogi.rest.service.util.HibernateStatisticsUtil;
import com.korogi.rest.service.util.MockMvcAssertionUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
                TestPersistenceConfig.class,
                CoreConfig.class,
                RestConfig.class
        }
)
@Transactional
@TestExecutionListeners(
        {
                DependencyInjectionTestExecutionListener.class,
                TransactionDbUnitTestExecutionListener.class
        }
)
@WebAppConfiguration
public abstract class BaseServiceTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private HibernateStatisticsInterceptor hibernateStatisticsInterceptor;

    private MockMvc mockMvc;

    protected HibernateStatisticsUtil hibernateStatisticsUtil;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .build();

        this.hibernateStatisticsUtil = new HibernateStatisticsUtil(hibernateStatisticsInterceptor);
    }

    protected MockMvcAssertionUtil performAndPrint(RequestBuilder requestBuilder) throws Exception {
        return new MockMvcAssertionUtil(
                this.mockMvc.perform(requestBuilder)
                    .andDo(print())
                    .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_UTF8))
        );
    }

    @AfterEach
    public void after() throws Exception {
        this.hibernateStatisticsUtil.resetQueryCount();
    }
}