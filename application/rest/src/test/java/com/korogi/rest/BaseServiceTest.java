package com.korogi.rest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import javax.transaction.Transactional;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.korogi.rest.security.TokenProvider;
import com.korogi.rest.util.HibernateStatisticsUtil;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

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
    protected TokenProvider tokenProvider;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        HibernateStatisticsUtil.resetQueryCount();
    }

    protected ResultActions performAndPrint(RequestBuilder requestBuilder) throws Exception {
        return this.mockMvc.perform(requestBuilder)
                           .andDo(print())
                           .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));
    }

    protected String generateToken(String providerId) {
        DefaultOidcUser oidcUser = mock(DefaultOidcUser.class);
        when(oidcUser.getName()).thenReturn(providerId);
        when(oidcUser.getEmail()).thenReturn(providerId + "@test.com");

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(oidcUser);

        return tokenProvider.createToken(authentication);
    }
}