package com.korogi.rest.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.korogi.core.interceptor.HibernateStatisticsInterceptor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateStatisticsUtil {

    public static void assertAmountOfQuerriesExecuted(long expectedAmount) {
        assertThat(HibernateStatisticsInterceptor.getQueryCount()).isEqualTo(expectedAmount);
    }

    public static void resetQueryCount() {
        HibernateStatisticsInterceptor.resetQueryCount();
    }
}