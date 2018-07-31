package com.korogi.rest.service;

import static com.korogi.dto.ErrorDTO.newErrorDTO;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.korogi.core.interceptor.HibernateStatisticsInterceptor;
import com.korogi.dto.ErrorDTO;
import com.korogi.rest.service.util.HibernateStatisticsUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:spring/test-rest-config.xml",
                "classpath:spring/test-persistence-config.xml"
        }
)
@Transactional
@TestExecutionListeners(
        {
                DependencyInjectionTestExecutionListener.class,
                DirtiesContextTestExecutionListener.class,
                TransactionDbUnitTestExecutionListener.class
        }
)
@WebAppConfiguration
public abstract class BaseServiceTest {
    protected static final String[] EXPECTED_ANIME_DETAILS_LINKS = new String[] { "self", "prequal", "sequal", "episodes" };
    protected static final String[] EXPECTED_EPISODE_DETAILS_LINKS = new String[] { "self" };

    protected static final ErrorDTO RESOURCE_NOT_FOUND_ERROR_DTO = newErrorDTO()
            .status("Not Found")
            .code(404)
            .message("Resource not found")
            .build();

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private HibernateStatisticsInterceptor hibernateStatisticsInterceptor;

    private MockMvc mockMvc;

    protected HibernateStatisticsUtil hibernateStatisticsUtil;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .build();

        this.hibernateStatisticsUtil = new HibernateStatisticsUtil(hibernateStatisticsInterceptor);
    }

    protected ResultActions performAndPrint(RequestBuilder requestBuilder) throws Exception {
        return this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_UTF8));
    }

    @After
    public void after() throws Exception {
        this.hibernateStatisticsUtil.resetQueryCount();
    }
}