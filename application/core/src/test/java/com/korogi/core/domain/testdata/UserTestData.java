package com.korogi.core.domain.testdata;

import com.korogi.core.domain.User;

/**
 * @author Daan Peelman
 */
public class UserTestData {
    public static User.UserBuilder johnDoe() {
        return User.newUser()
                   .providerId("1")
                   .email("john.doe@test.com")
                   .username("john.doe")
                   .biography("John Doe's Biography")
                   .salt(new byte[]{ 59, - 35, - 91, 68, - 26, - 16, 73, 108, 105, - 93, 93, 114, 118, 15, 112, - 32 })
                   .activationCode("123456789012345678901234567890123456")
                   .activated(false);
    }
}