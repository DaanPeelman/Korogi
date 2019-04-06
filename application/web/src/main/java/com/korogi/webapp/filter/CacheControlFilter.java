package com.korogi.webapp.filter;

import java.io.IOException;
import java.time.OffsetDateTime;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Daan Peelman
 */
public class CacheControlFilter implements Filter {
    private static final long AMOUNT_OF_SECONDS_TO_CACHE = ((60 * 60) * 24) * 7 * 4;
    private static final String HEADER_EXPIRES = "Expires";
    private static final String HEADER_CACHE_CONTROL = "Cache-Control";
    private static final String HEADER_CACHE_CONTROL_VALUE = "public, must-revalidate, max-age=" + AMOUNT_OF_SECONDS_TO_CACHE;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        long expiresEpochSeconds = OffsetDateTime.now().plusSeconds(AMOUNT_OF_SECONDS_TO_CACHE).toEpochSecond();

        httpServletResponse.setDateHeader(HEADER_EXPIRES, expiresEpochSeconds * 1000);
        httpServletResponse.setHeader(HEADER_CACHE_CONTROL, HEADER_CACHE_CONTROL_VALUE);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
