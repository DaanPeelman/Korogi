package com.korogi.core.domain.mother;

import static com.korogi.core.domain.builder.TestRoleBuilder.newTestRole;

import java.time.LocalDateTime;
import java.time.Month;
import com.korogi.core.domain.Role;
import com.korogi.core.domain.enumeration.RoleType;

/**
 * @author Daan Peelman
 */
public class RoleMother {
    public static Role admin() {
        return newTestRole()
                .roleType(RoleType.ADMIN)
                .build();
    }

    public static Role admin_persisted() {
        return newTestRole(admin())
                .id(1L)
                .creationDate(LocalDateTime.of(2017, Month.MARCH, 13, 0, 0))
                .createdBy("system")
                .version(1L)
                .build();
    }

    public static Role admin_updated() {
        return newTestRole(admin_persisted())
                .modificationDate(LocalDateTime.of(2017, Month.MARCH, 14, 0, 0))
                .modifiedBy("update.system")
                .version(2L)
                .build();
    }
}