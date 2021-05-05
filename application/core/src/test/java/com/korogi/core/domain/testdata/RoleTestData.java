package com.korogi.core.domain.testdata;

import static com.korogi.core.domain.Role.newRole;

import com.korogi.core.domain.Role;
import com.korogi.core.domain.enumeration.RoleType;

/**
 * @author Daan Peelman
 */
public class RoleTestData {
    public static Role.RoleBuilder admin() {
        return newRole()
            .roleType(RoleType.ADMIN);
    }
}