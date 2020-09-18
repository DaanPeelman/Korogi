package com.korogi.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import javax.transaction.Transactional;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.korogi.rest.util.HibernateStatisticsUtil;
import com.korogi.rest.util.MockMvcAssertionUtil;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

@SpringBootTest
@ActiveProfiles("unit-test")
@Transactional
@TestExecutionListeners(
    value = { DbUnitTestExecutionListener.class },
    mergeMode = MERGE_WITH_DEFAULTS
)
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase
public abstract class BaseServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        HibernateStatisticsUtil.resetQueryCount();
    }

    protected MockMvcAssertionUtil performAndPrint(RequestBuilder requestBuilder) throws Exception {
        return new MockMvcAssertionUtil(
                this.mockMvc.perform(requestBuilder)
                    .andDo(print())
                    .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
        );
    }
}