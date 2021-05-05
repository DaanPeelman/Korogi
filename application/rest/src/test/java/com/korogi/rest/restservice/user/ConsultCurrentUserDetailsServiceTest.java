package com.korogi.rest.restservice.user;

import static com.korogi.rest.util.CustomContentResultMatchers.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.korogi.rest.BaseServiceTest;
import org.junit.jupiter.api.Test;

/**
 * @author Daan Peelman
 */
public class ConsultCurrentUserDetailsServiceTest extends BaseServiceTest {
    private static final String URL = "/users/current";

    @Test
    void consultCurrentUserDetails_notAuthenticated() throws Exception {
        performAndPrint(get(URL))
            .andExpect(status().isUnauthorized())
            .andExpect(json().matchesFileContent("com/korogi/rest/restservice/unauthorized_expected.json"));
    }

    @Test
    void consultCurrentUserDetails_authenticatedNotRegisteredUser() throws Exception {
        performAndPrint(get(URL).header("Authorization", "Bearer " + generateToken("99")))
            .andExpect(status().isNotFound())
            .andExpect(json().matchesFileContent("com/korogi/rest/restservice/notFound_expected.json"));
    }

    @Test
    @DatabaseSetup("/com/korogi/rest/restservice/user/ConsultCurrentUserDetailsServiceTest_consultCurrentUserDetails_authenticatedRegisteredUser.xml")
    void consultCurrentUserDetails_authenticatedRegisteredUser() throws Exception {
        performAndPrint(get(URL).header("Authorization", "Bearer " + generateToken("1")))
            .andExpect(status().isOk())
            .andExpect(json().matchesFileContent(
                "com/korogi/rest/restservice/user/ConsultCurrentUserDetailsServiceTest_consultCurrentUserDetails_authenticatedRegisteredUser_expected.json"));
    }
}
