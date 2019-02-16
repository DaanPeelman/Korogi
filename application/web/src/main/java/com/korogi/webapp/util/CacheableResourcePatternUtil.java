package com.korogi.webapp.util;

/**
 * @author Daan Peelman
 */
public class CacheableResourcePatternUtil {
    private static final String RESOURCE_PATTERN = "^.+\\.(css|js)$";

    public boolean isCacheableResource(String request) {
        return request.matches(RESOURCE_PATTERN);
    }
}
