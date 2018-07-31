package com.korogi.rest.service.util;

import static org.fest.assertions.Assertions.assertThat;

import com.korogi.core.interceptor.HibernateStatisticsInterceptor;

public class HibernateStatisticsUtil {
    private final HibernateStatisticsInterceptor interceptor;

    public HibernateStatisticsUtil(HibernateStatisticsInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public void assertAmountOfQuerriesExecuted(long expectedAmount) {
        assertThat(interceptor.getQueryCount()).isEqualTo(expectedAmount);
    }

    public void resetQueryCount() {
        interceptor.resetQueryCount();
    }
}