package com.korogi.core.domain.mother;

import com.korogi.core.domain.Role;
import com.korogi.core.domain.builder.TestRoleBuilder;
import com.korogi.core.domain.enumeration.RoleType;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * @author Daan Peelman
 */
public class RoleMother {
    public static Role admin() {
        return new TestRoleBuilder()
                .roleType(RoleType.ADMIN)
                .build();
    }

    public static Role admin_persisted() {
        return new TestRoleBuilder(admin())
                .id(1L)
                .creationDate(LocalDateTime.of(2017, Month.MARCH, 13, 0, 0))
                .createdBy("system")
                .version(1L)
                .build();
    }

    public static Role admin_updated() {
        return new TestRoleBuilder(admin_persisted())
                .modificationDate(LocalDateTime.of(2017, Month.MARCH, 14, 0, 0))
                .modifiedBy("update.system")
                .version(2L)
                .build();
    }
}