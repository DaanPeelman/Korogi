package com.korogi.core.domain.testdata;

import com.korogi.core.domain.User;

/**
 * @author Daan Peelman
 */
public class UserTestData {
    public static User.UserBuilder johnDoe() {
        return User.newUser()
                .email("john.doe@test.com")
                .username("john.doe")
                .password("p@ssw0rd")
                .activationCode("123456789012345678901234567890123456")
                .activated(false);
    }
}