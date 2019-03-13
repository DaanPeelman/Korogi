package com.korogi.webapp.filter;

import static com.korogi.core.util.InjectorUtil.injectIntoStaticField;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.korogi.webapp.util.CacheableResourcePatternUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Daan Peelman
 */
@ExtendWith(MockitoExtension.class)
class CacheControlFilterTest {
    private static final long EXPECTED_MAX_AGE_IN_SECONDS = ((60 * 60) * 24) * (7 * 4);
    private static final String REQUEST_URI = "MY_REQUEST_URI";

    @InjectMocks
    private CacheControlFilter filter;

    @Mock
    private CacheableResourcePatternUtil cacheableResourcePatternUtil;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain chain;

    @BeforeEach
    void setup() throws Exception {
        injectIntoStaticField(filter.getClass(), "CACHEABLE_RESOURCE_PATTERN_UTIL", cacheableResourcePatternUtil);
        when(request.getRequestURI()).thenReturn(REQUEST_URI);
    }

    @Test
    void doFilter_cacheableResource() throws Exception {
        ArgumentCaptor<Long> captorExpiresDate = ArgumentCaptor.forClass(Long.class);

        when(cacheableResourcePatternUtil.isCacheableResource(REQUEST_URI)).thenReturn(true);
        doNothing().when(response).setDateHeader(eq("Expires"), captorExpiresDate.capture());

        long expectedExpiresValueLowerBound = OffsetDateTime.now().plusSeconds(EXPECTED_MAX_AGE_IN_SECONDS).toEpochSecond() * 1000;
        filter.doFilter(request, response, chain);
        long expectedExpiresValueUpperBound = OffsetDateTime.now().plusSeconds(EXPECTED_MAX_AGE_IN_SECONDS).toEpochSecond() * 1000;

        verify(response, times(1)).setDateHeader(eq("Expires"), anyLong());
        verify(response, times(1)).setHeader("Cache-Control", "public, must-revalidate, max-age=" + EXPECTED_MAX_AGE_IN_SECONDS);
        verify(chain, times(1)).doFilter(request, response);

        assertThat(captorExpiresDate.getValue())
            .isGreaterThanOrEqualTo(expectedExpiresValueLowerBound)
            .isLessThanOrEqualTo(expectedExpiresValueUpperBound);
    }

    @Test
    void doFilter_noCacheableResource() throws Exception {
        when(cacheableResourcePatternUtil.isCacheableResource(REQUEST_URI)).thenReturn(false);

        filter.doFilter(request, response, chain);

        verify(response, never()).setDateHeader(anyString(), anyLong());
        verify(response, never()).setHeader(anyString(), anyString());
        verify(chain, times(1)).doFilter(request, response);
    }
}
