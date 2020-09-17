package com.korogi.core.interceptor;

import java.util.concurrent.atomic.AtomicLong;
import org.hibernate.EmptyInterceptor;

public class HibernateStatisticsInterceptor extends EmptyInterceptor {
    private static final AtomicLong QUERY_COUNT = new AtomicLong(0L);

    @Override
    public String onPrepareStatement(String sql) {
        QUERY_COUNT.incrementAndGet();
        return super.onPrepareStatement(sql);
    }

    public static void resetQueryCount() {
        QUERY_COUNT.set(0L);
    }

    public static long getQueryCount() {
        return QUERY_COUNT.get();
    }
}