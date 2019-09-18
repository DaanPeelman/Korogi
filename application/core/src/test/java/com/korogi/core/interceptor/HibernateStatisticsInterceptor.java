package com.korogi.core.interceptor;

import java.util.concurrent.atomic.AtomicLong;
import org.hibernate.EmptyInterceptor;
import org.springframework.stereotype.Component;

@Component
public class HibernateStatisticsInterceptor extends EmptyInterceptor {
    private AtomicLong queryCount = new AtomicLong(0L);

    @Override
    public String onPrepareStatement(String sql) {
        queryCount.incrementAndGet();

        return super.onPrepareStatement(sql);
    }

    public void resetQueryCount() {
        queryCount.set(0L);
    }

    public long getQueryCount() {
        return queryCount.get();
    }
}